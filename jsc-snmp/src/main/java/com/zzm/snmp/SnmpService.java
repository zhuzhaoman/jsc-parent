package com.zzm.snmp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.ChassisTypeEnum;
import com.zzm.enums.PortOIDEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.PortMessageVO;
import com.zzm.pojo.vo.SlotPortStatusVO;
import com.zzm.policy.snmp.port_status.SnmpPortStatusCardTypeComponent;
import com.zzm.policy.snmp.port_status.SnmpPortStatusCardTypePolicyService;
import com.zzm.resource.SnmpResource;
import com.zzm.service.DeviceService;
import com.zzm.utils.BaseConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

@SuppressWarnings("all")
@Component
public class SnmpService {

    public static final Logger log = LoggerFactory.getLogger(SnmpService.class);

    private Object deviceInfo = null;

    @Resource
    private SnmpDao snmpDao;

    @Resource
    private SnmpResource snmpResource;

    @Resource
    private DeviceService deviceService;

    /**
     * 测试网络是否连通
     *
     * @return
     * @throws IOException
     */
    public boolean isEthernetConnection() {
        try {
            InetAddress inetAddress = InetAddress.getByName(snmpResource.getHostIp());
            return inetAddress.isReachable(1000);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 获取全部端口状态
     */
    public List<Map<String, Object>> allSlotPortStatus() throws Exception {

        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }

        // 获取所有端口状态信息
        List<String> portStatusList = snmpDao.walkByTableToList(PortOIDEnum.PORT_LINK_STATUS.getCode());
//        List<String> portStatusList = this.getPortLinkStatus();

        // 获取所有端口丝印号
        List<String> portIndexList = snmpDao.walkByTableToList(PortOIDEnum.PORT_INDEX.getCode());
//        List<String> portIndexList = this.getPortIndex();
        if (portStatusList.size() != portIndexList.size()) {
            log.error("【异常：端口不匹配】");
            return null;
        }

        if (portStatusList.size() == 0) {
            log.error("【异常：端口不匹配】");
            return null;
        }

        ReceiveSystemManagerDTO receiveSystemManagerDTO = deviceService.info("admin");
//        ReceiveSystemManagerDTO receiveSystemManagerDTO = this.getDeviceInfo();
        Object deviceInfo = receiveSystemManagerDTO.getData();
        this.deviceInfo = deviceInfo;


        int slotTotal = getDeviceSlotTotal();

        /**
         * 初始化数据
         */
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < slotTotal; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("list", new ArrayList<SlotPortStatusVO>());
            map.put("slot", null);
            map.put("cardType", null);
            result.add(i, map);
        }

        for (int index = 0; index < portIndexList.size(); index++) {

            /**
             * [24-31]：8位 -> 端口号
             * [23-19]：5位 -> 扩展端口号
             *    [18]：1位 -> 端口是否甩纤：甩纤1 、未甩纤0
             * [17-16]：2位 -> 端口类型，前面板端口（00），后面板端口（1）、100G端口（2）
             * [15-11]：5位 -> 端口所属前面板槽位
             *  [10-8]：3位 -> 端口所在后面板槽位号（rtm板卡）
             *   [7-3]：5位 -> 端口所在机箱号
             *   [2-0]：3位 -> 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
             */

            /**
             * 获取丝印号,并将其转换为16进制：5221321
             */
            int indexNumber = Integer.parseInt(portIndexList.get(index));
            String indexStr = BaseConversionUtils.decimal2binary(indexNumber, 32);

            /**
             * 所属槽位：1，2，....
             */
            int slotStart = 11;
            int slotEnd = slotStart + 5;
            int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);

            /**
             * 获取端口号，并将其转换为：1、2、3
             */
            int portNumber = Integer.parseInt(indexStr.substring(indexStr.length() - 8), 2);

            /**
             * 端口速率
             */
            int portRateStart = indexStr.length() - 32;
            int portRateEnd = portRateStart + 3;
            int portRate = Integer.parseInt(indexStr.substring(portRateStart, portRateEnd), 2);


            /**
             * 是否甩纤,：0、1
             */
            int start = indexStr.length() - 14;
            int end = start + 1;
            int isEXTEND = Integer.parseInt(indexStr.substring(start, end), 2);

            /**
             * 获取扩展端口号
             */
            int extendStart = indexStr.length() - 13;
            int extendEnd = extendStart + 5;
            String extendStr = indexStr.substring(extendStart, extendEnd);
            int extendPortNumber = Integer.parseInt(extendStr, 2);

            if (slotNumber <= slotTotal) {
                int cardType = getCardType(slotNumber);

                SnmpPortStatusCardTypePolicyService snmpPortStatusCardTypePolicyService =
                        SnmpPortStatusCardTypeComponent.snmpCardTypePolicyServiceMap.get(cardType);

                snmpPortStatusCardTypePolicyService.dataEncapsulation(indexNumber, slotNumber, portNumber, portRate, isEXTEND, extendPortNumber, result, index, portStatusList);
            }

        }

        return result;
    }

    private List<String> getPortLinkStatus() {
        List<String> result = Arrays.asList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "1", "1", "0", "0", "0");
        return result;
    }

    private List<String> getPortIndex() {
        List<String> result = Arrays.asList("553713665", "553713666", "553713667", "553713668", "553713669", "553713670", "553713671", "553713672", "553713673", "553713674", "553713675", "553713676", "553713677", "553713678", "553713679", "553713680", "553713681", "553713682", "553713683", "553713684", "553713685", "553713686", "553713687", "553713688", "553713689", "553713690", "553713691", "553713692", "553713693", "553713694", "553713695", "553713696", "553713697", "553713698", "553713699", "553713700", "553779201", "553779202", "553779203", "553779204", "553779205", "553779206", "553779207", "553779208", "553779209", "553779210", "553779211", "553779212", "553779213", "553779214", "553779215", "553779216", "553779217", "553779218", "553779219", "553779220", "553779221", "553779222", "553779223", "553779224", "553779225", "553779226", "553779227", "553779228", "553779229", "553779230", "553779231", "553779232", "553779233", "553779234", "553779235", "553779236", "553844737", "553844738", "553844739", "553844740", "553844741", "553844742", "553844743", "553844744", "553844745", "553844746", "553844747", "553844748", "553844749", "553844750", "553844751", "553844752", "553844753", "553844754", "553844755", "553844756", "553844757", "553844758", "553844759", "553844760", "553844761", "553844762", "553844763", "553844764", "553844765", "553844766", "553844767", "553844768", "553844769", "553844770", "553844771", "553844772", "553910273", "553910274", "553910275", "553910276", "553910277", "553910278", "553910279", "553910280", "553910281", "553910282", "553910283", "553910284", "553910285", "553910286", "553910287", "553910288", "553910289", "553910290", "553910291", "553910292", "553910293", "553910294", "553910295", "553910296", "553910297", "553910298", "553910299", "553910300", "553910301", "553910302", "553910303", "553910304", "553910305", "553910306", "553910307", "553910308", "553975809", "553975810", "553975811", "553975812", "553975813", "553975814", "553975815", "553975816", "553975817", "553975818", "553975819", "553975820", "553975821", "553975822", "553975823", "553975824", "553975825", "553975826", "553975827", "553975828", "553975829", "553975830", "553975831", "553975832", "553975833", "553975834", "553975835", "553975836", "553975837", "553975838", "553975839", "553975840", "553975841", "553975842", "553975843", "553975844", "554041345", "554041346", "554041347", "554041348", "554041349", "554041350", "554041351", "554041352", "554041353", "554041354", "554041355", "554041356", "554041357", "554041358", "554041359", "554041360", "554041361", "554041362", "554041363", "554041364", "554041365", "554041366", "554041367", "554041368", "554041369", "554041370", "554041371", "554041372", "554041373", "554041374", "554041375", "554041376", "554041377", "554041378", "554041379", "554041380", "554106881", "554106882", "554106883", "554106884", "554106885", "554106886", "554106887", "554106888", "554106889", "554106890", "554106891", "554106892", "554106893", "554106894", "554106895", "554106896", "554106897", "554106898", "554106899", "554106900", "554106901", "554106902", "554106903", "554106904", "554106905", "554106906", "554106907", "554106908", "554106909", "554106910", "554106911", "554106912", "554106913", "554106914", "554106915", "554106916", "554172417", "554172418", "554172419", "554172420", "554172421", "554172422", "554172423", "554172424", "554172425", "554172426", "554172427", "554172428", "554172429", "554172430", "554172431", "554172432", "554172433", "554172434", "554172435", "554172436", "554172437", "554172438", "554172439", "554172440", "554172441", "554172442", "554172443", "554172444", "554172445", "554172446", "554172447", "554172448", "554172449", "554172450", "554172451", "554172452", "1090584577", "1090584578", "1090584579", "1090584580", "1090584581", "1090584582", "1090584583", "1090584584", "1090584585", "1090584586", "1090584587", "1090584588", "1090584589", "1090584590", "1090584591", "1090584592", "1090584593", "1090584594", "1090584595", "1090584596", "1090584597", "1090584598", "1090584599", "1090584600", "1090650113", "1090650114", "1090650115", "1090650116", "1090650117", "1090650118", "1090650119", "1090650120", "1090650121", "1090650122", "1090650123", "1090650124", "1090650125", "1090650126", "1090650127", "1090650128", "1090650129", "1090650130", "1090650131", "1090650132", "1090650133", "1090650134", "1090650135", "1090650136", "1090715649", "1090715650", "1090715651", "1090715652", "1090715653", "1090715654", "1090715655", "1090715656", "1090715657", "1090715658", "1090715659", "1090715660", "1090715661", "1090715662", "1090715663", "1090715664", "1090715665", "1090715666", "1090715667", "1090715668", "1090715669", "1090715670", "1090715671", "1090715672", "1090781185", "1090781186", "1090781187", "1090781188", "1090781189", "1090781190", "1090781191", "1090781192", "1090781193", "1090781194", "1090781195", "1090781196", "1090781197", "1090781198", "1090781199", "1090781200", "1090781201", "1090781202", "1090781203", "1090781204", "1090781205", "1090781206", "1090781207", "1090781208", "1090846721", "1090846722", "1090846723", "1090846724", "1090846725", "1090846726", "1090846727", "1090846728", "1090846729", "1090846730", "1090846731", "1090846732", "1090846733", "1090846734", "1090846735", "1090846736", "1090846737", "1090846738", "1090846739", "1090846740", "1090846741", "1090846742", "1090846743", "1090846744", "1090912257", "1090912258", "1090912259", "1090912260", "1090912261", "1090912262", "1090912263", "1090912264", "1090912265", "1090912266", "1090912267", "1090912268", "1090912269", "1090912270", "1090912271", "1090912272", "1090912273", "1090912274", "1090912275", "1090912276", "1090912277", "1090912278", "1090912279", "1090912280", "1090977793", "1090977794", "1090977795", "1090977796", "1090977797", "1090977798", "1090977799", "1090977800", "1090977801", "1090977802", "1090977803", "1090977804", "1090977805", "1090977806", "1090977807", "1090977808", "1090977809", "1090977810", "1090977811", "1090977812", "1090977813", "1090977814", "1090977815", "1090977816", "1091043329", "1091043330", "1091043331", "1091043332", "1091043333", "1091043334", "1091043335", "1091043336", "1091043337", "1091043338", "1091043339", "1091043340", "1091043341", "1091043342", "1091043343", "1091043344", "1091043345", "1091043346", "1091043347", "1091043348", "1091043349", "1091043350", "1091043351", "1091043352");
        return result;
    }

    private ReceiveSystemManagerDTO getDeviceInfo() {
        String text = "{\"code\":200,\"msg\":\"获取设备信息成功！！！\",\"messageCode\":36612,\"username\":\"user1\",\"domainId\":0,\"domainType\":0,\"data\":{\"m_u32MaxMemory\":7955,\"m_u32MaxFanNum\":48,\"m_u32EquId\":1,\"m_u32DiskUsagePS\":11,\"m_u32EquType\":6,\"m_strSoftwareVersion\":\"v3.6.0-P2\",\"m_u32NormalFanNum\":48,\"m_u32MaxPowerNum\":5,\"m_tCardStatusMsg\":[{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021200003\",\"m_u32RoleType\":5,\"m_u32SlotId\":1,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021200001\",\"m_u32RoleType\":5,\"m_u32SlotId\":2,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021200002\",\"m_u32RoleType\":5,\"m_u32SlotId\":3,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021220003\",\"m_u32RoleType\":5,\"m_u32SlotId\":4,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021220002\",\"m_u32RoleType\":5,\"m_u32SlotId\":5,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021200003\",\"m_u32RoleType\":5,\"m_u32SlotId\":6,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021130004\",\"m_u32RoleType\":5,\"m_u32SlotId\":7,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2232100021200006\",\"m_u32RoleType\":5,\"m_u32SlotId\":8,\"m_u32CardType\":18},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2221110021220001\\n\",\"m_u32RoleType\":8,\"m_u32SlotId\":17,\"m_u32CardType\":19},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2221110021220001\\n\",\"m_u32RoleType\":8,\"m_u32SlotId\":18,\"m_u32CardType\":19},{\"m_u32RunStatus\":4,\"m_strCardSerialNum\":\"2221110021220001\\n\",\"m_u32RoleType\":8,\"m_u32SlotId\":19,\"m_u32CardType\":19},{\"m_u32RunStatus\":0,\"m_strCardSerialNum\":\"0\",\"m_u32RoleType\":0,\"m_u32SlotId\":20,\"m_u32CardType\":0},{\"m_u32RunStatus\":0,\"m_strCardSerialNum\":\"0\",\"m_u32RoleType\":0,\"m_u32SlotId\":21,\"m_u32CardType\":0}],\"m_tGuiCfgMsg\":{\"m_u32Enable\":0},\"m_u32CpuUsagePS\":37,\"m_u32MemoryUsedPercent\":36,\"m_strSystemTime\":\"2019-01-02 07:32:46\",\"m_u32NormalPowerNum\":5,\"m_strRunTime\":\"0days 10hours 3mins\"}}";
        JSONObject jsonObject = JSONObject.parseObject(text);
        ReceiveSystemManagerDTO receiveSystemManagerDTO = JSONObject.toJavaObject(jsonObject, ReceiveSystemManagerDTO.class);
        return receiveSystemManagerDTO;
    }

    /**
     * 获取某个端口状态，包括甩纤
     *
     * @param portIndexList：端口丝印号
     * @return
     */
    public List<PortMessageVO> appointStatus(String portIndexList) {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }

        String[] portIndex = portIndexList.split(",");
        List<PortMessageVO> portMessageVOList = new ArrayList<>();

        for (int i = 0; i < portIndex.length; i++) {

            int indexNumber = Integer.parseInt(portIndex[i]);
            String indexStr = Integer.toBinaryString(indexNumber);

            // 获取端口号
            int portNumber = Integer.parseInt(indexStr.substring(indexStr.length() - 8), 2);

            String portInBytesRate = PortOIDEnum.PORT_IN_BYTES_RATE.getCode() + "." + portIndex[i];
            String portOutBytesRate = PortOIDEnum.PORT_OUT_BYTES_RATE.getCode() + "." + portIndex[i];
            String portInOpticalPower = PortOIDEnum.PORT_IN_OPTICAL_POWER.getCode() + "." + portIndex[i];
            String portOutOpticalPower = PortOIDEnum.PORT_OUT_OPTICAL_POWER.getCode() + "." + portIndex[i];
            String portSpeedOid = PortOIDEnum.PORT_SPEED.getCode() + "." + portIndex[i];
            String portStatusOid = PortOIDEnum.PORT_LINK_STATUS.getCode() + "." + portIndex[i];

            List<String> IBytesList = snmpDao.walkByTableToOne(portInBytesRate); // 输入流量
            List<String> OBytesList = snmpDao.walkByTableToOne(portOutBytesRate); // 输出流量
            List<String> IOpticalPowerList = snmpDao.walkByTableToOne(portInOpticalPower); // 输入流量
            List<String> OOpticalPowerList = snmpDao.walkByTableToOne(portOutOpticalPower); // 输出流量
            List<String> portSpeedList = snmpDao.walkByTableToOne(portSpeedOid); // 端口速率
            List<String> portStatusList = snmpDao.walkByTableToOne(portStatusOid); // 端口状态

            // 判断端口是否甩纤
            String portNumberStr = portIndex.length > 1 ? (portNumber + "_" + (i + 1)) : String.valueOf(portNumber);

            PortMessageVO portMessageVO = new PortMessageVO();
            portMessageVO.setPortNumber(portNumberStr); // 1_1、1_2或者1、2
            portMessageVO.setInputBytes(IBytesList.get(0));
            portMessageVO.setOutputBytes(OBytesList.get(0));
            portMessageVO.setInputOpticalPower(IOpticalPowerList.get(0));
            portMessageVO.setOutputOpticalPower(OOpticalPowerList.get(0));
            portMessageVO.setPortSpeed(Integer.parseInt(portSpeedList.get(0)) / 1000 + "G"); // 10G
            portMessageVO.setPortStatus(portStatusList.get(0));

            portMessageVOList.add(portMessageVO);
        }

        return portMessageVOList;
    }

    /**
     * 端口输入字节数
     *
     * @return
     */
    public List<String> getInBytes() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_IN_BYTES.getCode());
    }

    /**
     * 端口输出字节数
     *
     * @return
     */
    public List<String> getOutBytes() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_OUT_BYTES.getCode());
    }


    /**
     * 端口输入光功率
     *
     * @return
     */
    public List<String> getInOpticalPower() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_IN_OPTICAL_POWER.getCode());
    }

    /**
     * 端口输出光功率
     *
     * @return
     */
    public List<String> getOutOpticalPower() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_OUT_OPTICAL_POWER.getCode());
    }

    /**
     * 输入字节速率
     *
     * @return
     */
    public List<String> getInputRate() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_IN_BYTES_RATE.getCode());
    }

    /**
     * 输出字节速率
     *
     * @return
     */
    public List<String> getOutputRate() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }
        return snmpDao.walkByTableToList(PortOIDEnum.PORT_OUT_BYTES_RATE.getCode());
    }

    /**
     * 获取全部端口号
     *
     * @return
     */
    public List<String> getPortIndexAll() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }
        return snmpDao.walkByTableToList(PortOIDEnum.PORT_INDEX.getCode());
    }

    /**
     * 获取设备槽位数
     *
     * @return
     */
    private int getDeviceSlotTotal() {
        JSONObject jsonObject = JSON.parseObject(deviceInfo.toString());
        int chassisType = (int) jsonObject.get("m_u32EquType");

        ChassisTypeEnum chassisTypeEnum = ChassisTypeEnum.fromValue(chassisType);

        return chassisTypeEnum.getSlotNumber();
    }

    /**
     * 根据槽位获取当前板卡的类型
     *
     * @param slotNumber
     * @return
     */
    private int getCardType(int slotNumber) {
        JSONObject jsonObject = JSON.parseObject(deviceInfo.toString());
        JSONArray cardSlotList = jsonObject.getJSONArray("m_tCardStatusMsg");

        for (int i = 0; i < cardSlotList.size(); i++) {
            JSONObject slot = cardSlotList.getJSONObject(i);
            if ((int) slot.get("m_u32SlotId") == slotNumber) {
                int slotType = (int) slot.get("m_u32CardType");
                return slotType;
            }
        }

        return 0;
    }

}
