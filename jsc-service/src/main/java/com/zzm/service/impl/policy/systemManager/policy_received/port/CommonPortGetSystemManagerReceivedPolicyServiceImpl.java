package com.zzm.service.impl.policy.systemManager.policy_received.port;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 用户登录，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class CommonPortGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_INTERFACE_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) throws UnknownHostException {

        if (receiveSystemManagerDTO.getCode() == 200) {

            System.out.println(receiveSystemManagerDTO.getData().toString());
            JSONArray result = JSONArray.parseArray(JSONObject.parse((receiveSystemManagerDTO.getData()).toString()).toString());
            if (receiveSystemManagerDTO.getMessageCode() == 34348) {
                result = showPortGre(result);
            }

            if (receiveSystemManagerDTO.getMessageCode() == 34562) {
                result = showPortInfo(result);
            }

            receiveSystemManagerDTO.setData(result);
            receiveSystemManagerDTO.setMsg("端口配置获取成功！");
        }

        return receiveSystemManagerDTO;
    }


    /**
     * 查询端口信息
     *
     * @param data
     * @return
     */
    private JSONArray showPortInfo(JSONArray data) throws UnknownHostException {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data.get(0)));

        String ipv6Sip = IPUtils.base64ToIp(jsonObject.getString("m_strIpv6Sip"));
        String ipv6Dip = IPUtils.base64ToIp(jsonObject.getString("m_strIpv6Dip"));
        jsonObject.put("m_strIpv6Sip", ipv6Sip);
        jsonObject.put("m_strIpv6Dip", ipv6Dip);

        String ipv4Sip = BaseConversionUtils.long2Ip(jsonObject.getLong("m_u32Ipv4Sip"));
        String ipv4Dip = BaseConversionUtils.long2Ip(jsonObject.getLong("m_u32Ipv4Dip"));
        jsonObject.put("m_u32Ipv4Sip", ipv4Sip);
        jsonObject.put("m_u32Ipv4Dip", ipv4Dip);

//        byte[] greSrcMac = Base64.getDecoder().decode(jsonObject.getString("m_strGreSrcMac"));
//        byte[] greDstMac = Base64.getDecoder().decode(jsonObject.getString("m_strGreDstMac"));

        jsonObject.put("m_strGreSrcMac", jsonObject.getString("m_strGreSrcMac"));
        jsonObject.put("m_strGreDstMac", jsonObject.getString("m_strGreDstMac"));

//        jsonObject.put("m_strGreSrcMac", new String(greSrcMac));
//        jsonObject.put("m_strGreDstMac", new String(greDstMac));

        String port = portHandel(jsonObject.getInteger("m_u32PortId"));
        jsonObject.put("m_u32PortId", port);

        if (jsonObject.getInteger("m_u32ForwardingPortId") != 0) {
            String forwardPort = portHandel(jsonObject.getInteger("m_u32ForwardingPortId"));
            jsonObject.put("m_u32ForwardingPortId", forwardPort);
        }

        try {
            String mirrorPort = portHandel(jsonObject.getInteger("m_u32MirrorPortId"));
            jsonObject.put("m_u32MirrorPortId", mirrorPort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("m_tPortRoute")));
            jsonObject1.put("m_u32SrcIp", BaseConversionUtils.long2Ip(jsonObject1.getLong("m_u32SrcIp")));
            jsonObject1.put("m_u32DstIp", BaseConversionUtils.long2Ip(jsonObject1.getLong("m_u32DstIp")));
            String srcMac = (String) jsonObject1.getString("m_strSrcMac");
            String dstMac = jsonObject1.getString("m_strDstMac");

            String[] srcArr = new String[srcMac.length() / 2];
            String[] dstArr = new String[dstMac.length() / 2];
            for (int i = 0; i < srcArr.length; i++) {
                srcArr[i] = srcMac.substring(i * 2, (i + 1) * 2);
                dstArr[i] = dstMac.substring(i * 2, (i + 1) * 2);
            }

            jsonObject1.put("m_strSrcMac", String.join(":", srcArr));
            jsonObject1.put("m_strDstMac", String.join(":", dstArr));

            jsonObject.put("m_tPortRoute", jsonObject1);
        } catch (Exception e) {
            System.out.println("没有路由信息");
        }

        data.set(0, jsonObject);

        return data;
    }

    private static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    public static List<String> getStrList(String inputString, int length, int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = inputString.substring(index * length, (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 查询gre隧道信息
     *
     * @param data
     * @return
     */
    private JSONArray showPortGre(JSONArray data) {
        JSONArray result = new JSONArray();

        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data.get(i)));
            JSONArray jsonArray1 = JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("m_tIpInfo")));

            if (jsonArray1.size() > 0) {
                System.out.println(jsonArray1.toJSONString());
                String port = portHandel((Integer) jsonObject.get("m_u32PortId"));
                jsonObject.put("m_u32PortId", port);

                for (int i1 = 0; i1 < jsonArray1.size(); i1++) {
                    JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonArray1.get(i1)));

                    if (StringUtils.isNotBlank((String) jsonObject1.get("m_strIpv6"))) {
                        byte[] ipv6Byte = Base64.getDecoder().decode((String) jsonObject1.get("m_strIpv6"));
                        String ipv6 = null;
                        try {
                            ipv6 = InetAddress.getByAddress(ipv6Byte).getHostAddress();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                            ipv6 = "";
                        }
                        jsonObject1.put("m_strIpv6", ipv6);
                    } else {
                        String ipv4 = BaseConversionUtils.long2Ip(Long.parseLong(String.valueOf(jsonObject1.get("m_u32Ipv4"))));
                        jsonObject1.put("m_u32Ipv4", ipv4);
                    }

                    byte[] macByte = Base64.getDecoder().decode((String) jsonObject1.get("m_strMac"));
                    String[] macArr = new String[macByte.length];
                    for (int j = 0; j < macByte.length; j++) {
                        String s = Integer.toHexString(macByte[j]).replace("f", "");
                        macArr[j] = s;
                    }

                    jsonObject1.put("m_strMac", String.join(":", macArr));
                    jsonArray1.set(i1, jsonObject1);
                }

                jsonObject.put("m_tIpInfo", jsonArray1);
                result.add(jsonObject);
            }
        }

        return result;
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(String.join(":", new String("01020304").split(" ", 2)).substring(1));
        System.out.println(getStrList("0102030405", 2));
        //"http://restapi.amap.com/v3/geocode/geo?address=上海市东方明珠&output=JSON&key=xxxxxxxxx";
//        String geturl = "http://restapi.amap.com/v3/geocode/geo?key=b6235ca589ee3611762e4fdac357034e&address=江苏省宿迁市泗阳县";
//        String location = "";
//        try {
//            URL url = new URL(geturl);    // 把字符串转换为URL请求地址
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
//            connection.connect();// 连接会话
//            // 获取输入流
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {// 循环读取流
//                sb.append(line);
//            }
//            br.close();// 关闭流
//            connection.disconnect();// 断开连接
//            JSONObject a = JSON.parseObject(sb.toString());
//            //判断输入的位置点是否存在
//            System.out.println(sb.toString());
//            if(a.getJSONArray("geocodes").size()>0) {
//                location=a.getJSONArray("geocodes").getJSONObject(0).get("location").toString();
//                System.out.println(location);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("失败!");
//        }


    }

    private static String portHandel(Integer indexNumber) {

        String indexStr = BaseConversionUtils.decimal2binary(indexNumber, 32);

        /**
         * 机箱号
         */
        int chassisStart = 3;
        int chassisEnd = chassisStart + 5;
        int chassisNumber = Integer.parseInt(indexStr.substring(chassisStart, chassisEnd));
        /**
         * 所属槽位
         */
        int slotStart = 11;
        int slotEnd = slotStart + 5;
        int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);
        /**
         * 端口号
         */
        int portStart = 24;
        int portEnd = portStart + 8;
        int portNumber = Integer.parseInt(indexStr.substring(portStart, portEnd), 2);
        /**
         * 扩展端口号
         */
        int extendStart = 19;
        int extendEnd = extendStart + 5;
        int extendNumber = Integer.parseInt(indexStr.substring(extendStart, extendEnd), 2);
        /**
         * 端口速率
         */
        int portRateStart = 0;
        int portRateEnd = portRateStart + 3;
        int portRate = Integer.parseInt(indexStr.substring(portRateStart, portRateEnd), 2);
        /**
         * 是否甩纤
         */
        int isEXTENDStart = 18;
        int isEXTENDEnd = isEXTENDStart + 1;
        int isEXTEND = Integer.parseInt(indexStr.substring(isEXTENDStart, isEXTENDEnd), 2);

        /**
         * 判断端口的类型
         * 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
         */
        String flag = (portRate == 1 ? "S " : portRate == 2 ? "Q " : "");


        // 端口名称
        String portName = "";

        if (isEXTEND == 1) {
            portName = flag + " front " + chassisNumber + "/" + slotNumber + "/" + portNumber + "_" + extendNumber;
        } else {
            portName = flag + " front " + chassisNumber + "/" + slotNumber + "/" + portNumber;
        }

        return portName;
    }
}
