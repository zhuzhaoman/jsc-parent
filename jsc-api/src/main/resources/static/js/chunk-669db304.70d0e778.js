(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-669db304"],{"08d2":function(e,a,l){"use strict";l.d(a,"a",(function(){return o})),l.d(a,"b",(function(){return r}));var t=l("b775");function o(e){return Object(t["a"])({url:"/domain/all",method:"get",params:{username:e}})}function r(e){return Object(t["a"])({url:"/domain/get",method:"post",data:e})}},"49a9":function(e,a,l){},"8a72":function(e,a,l){"use strict";var t=l("49a9"),o=l.n(t);o.a},b580:function(e,a,l){"use strict";l.r(a);var t=function(){var e=this,a=e.$createElement,l=e._self._c||a;return l("div",{staticClass:"real-time-flow-container"},[l("el-divider",{attrs:{"content-position":"left"}},[l("span",{staticStyle:{"font-size":"16px","font-weight":"bold"}},[e._v("端口实时流量")])]),l("el-card",{staticClass:"card",staticStyle:{"border-radius":"15px"},attrs:{shadow:"hover","body-style":{padding:"20px",margin:"0",width:"100%"}}},[l("div",{staticClass:"real-time-flow-search-container"},[l("div",{staticClass:"search"},[l("el-cascader",{staticStyle:{"margin-right":"5px",width:"250px"},attrs:{placeholder:"请选择域",options:e.domain,props:{expandTrigger:"hover"}},model:{value:e.domainValue,callback:function(a){e.domainValue=a},expression:"domainValue"}}),l("el-input",{staticClass:"input",attrs:{placeholder:"请输入板卡号或端口",clearable:""},model:{value:e.param,callback:function(a){e.param=a},expression:"param"}}),l("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.show()}}},[e._v("立即查询")])],1),l("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.realTimeFlow,stripe:"","header-cell-style":{background:"#f5f7fa"},height:"480px"}},[l("el-table-column",{attrs:{type:"expand"},scopedSlots:e._u([{key:"default",fn:function(a){return[l("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[l("el-form-item",{attrs:{label:" 输入速率(bps):"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64InBitRate).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输出速率(bps):"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64OutBitRate).toLocaleString()))])]),l("el-form-item",{attrs:{label:"输入包速率(pps):"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64InPktRate).toLocaleString()))])]),l("el-form-item",{attrs:{label:"输出包速率(pps):"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64OutPktRate).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输 入 包 数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64InPckts).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输 出 包 数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64OutPckts).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输入丢包数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64InDiscards).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输出丢包数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64OutDiscards).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输入错包数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64InErrors).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输出错包数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64OutErrors).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输入字节数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64InBytes).toLocaleString()))])]),l("el-form-item",{attrs:{label:" 输出字节数:"}},[l("span",[e._v(e._s(parseFloat(a.row.m_u64OutBytes).toLocaleString()))])])],1)]}}])}),l("el-table-column",{attrs:{label:"端口号",prop:"portId",align:"center"}}),l("el-table-column",{attrs:{label:"输入速率(bps)",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64InBitRate).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输出速率(bps)",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64OutBitRate).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输入包速率(pps)",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64InPktRate).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输出包速率(pps)",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64OutPktRate).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输入丢包数",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64InDiscards).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输出丢包数",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64OutDiscards).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输入错包数",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64InErrors).toLocaleString())+" ")]}}])}),l("el-table-column",{attrs:{label:"输出错包数",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(parseFloat(a.row.m_u64OutErrors).toLocaleString())+" ")]}}])})],1)],1)])],1)},o=[],r=(l("4160"),l("45fc"),l("159b"),l("96cf"),l("1da1")),n=l("5f87"),u=l("08d2"),s=l("b775");function i(e){return Object(s["a"])({url:"/realTimeFlow/get",method:"post",data:e})}var c=l("f698"),d={name:"port-flow-search",data:function(){return{domainValue:[],domain:[],domainType:[],param:null,realTimeFlow:[],loading:!1}},created:function(){this.domainType=c["e"];var e=Object(n["a"])();this._getDomainAll(e)},methods:{show:function(){var e=this,a=this.domainValue;if(a.length<3||null==this.param)this.$message({message:"请选择要查选的域信息",type:"warning"});else{this.realTimeFlow=[],this.loading=!0;var l=Object(n["a"])(),t={username:l,domainType:a[0],domainId:a[1],type:a[2],param:this.param};i(t).then((function(a){e.loading=!1,e.realTimeFlow=a.data}))}},_getDomainAll:function(e){var a=this;return Object(r["a"])(regeneratorRuntime.mark((function l(){var t;return regeneratorRuntime.wrap((function(l){while(1)switch(l.prev=l.next){case 0:return t=[],l.next=3,Object(u["a"])(e).then((function(e){var l=e.data;console.log(l),l.forEach((function(e,l){var o={};a.domainType.some((function(a,l){if(e.m_u32Property===a.value)return o.label=a.label,o.value=a.value,o.children=[{label:e.m_u32DomainId,value:e.m_u32DomainId,children:[{value:"slot",label:"槽位"},{value:"input",label:"输入端口组"},{value:"output",label:"输出端口组"}]}],!0})),t.push(o)}))}));case 3:a.domain=t;case 4:case"end":return l.stop()}}),l)})))()}}},_=d,m=(l("8a72"),l("2877")),p=Object(m["a"])(_,t,o,!1,null,"5873d4de",null);a["default"]=p.exports},f698:function(e,a,l){"use strict";l.d(a,"c",(function(){return t})),l.d(a,"b",(function(){return o})),l.d(a,"e",(function(){return r})),l.d(a,"a",(function(){return n})),l.d(a,"d",(function(){return u}));var t=[{code:0,value:"UNKNOWN_CARD"},{code:1,value:"NGP_PPB3320"},{code:2,value:"NGP_PPB3310"},{code:3,value:"NGP_PPB3311"},{code:4,value:"NGP_PPB3312"},{code:5,value:"NGP_PPB3313"},{code:6,value:"NGP_CPB4140"},{code:7,value:"NGP_SMB2008"},{code:8,value:"NGP_IOB6210"},{code:9,value:"NGP_CPB4141"},{code:10,label:"主控卡",value:"NETVIS-MCB1901"},{code:11,label:"交互卡",value:"NETVIS-SMB2032"},{code:12,label:"双路业务板卡",value:"NETVIS-PPB3320"},{code:13,label:"后插卡",value:"NETVIS-RTM9010"},{code:14,label:"32口盒式设备",value:"NETVIS-BB2032"},{code:255,value:"ALL_PROCESS_CARD"}],o=[{code:0,label:"No Card",value:"NO_CARD"},{code:1,label:"Uninit",value:"UNINIT_STATUS"},{code:2,label:"Init",value:"INIT_STATUS"},{code:3,label:"GlobalSYN",value:"GLOBAL_SYN_STATUS"},{code:4,label:"Ready",value:"READY_STATUS"},{code:5,label:"TimeSYN",value:"TIME_SYN_STATUS"},{code:6,label:"Del All Rule",value:"DELETE_ALL_RULE"},{code:7,label:"Resume Default",value:"RESUME_DEFAULT"},{code:8,label:"BUSY_STATUS",value:"BUSY_STATUS"},{code:255,value:"ERROR_STATUS"}],r=[{value:0,label:"UNKNOWN"},{value:1,label:"SWITCH"},{value:2,label:"COLLECTION"},{value:3,label:"DPI"},{value:4,label:"LTE"},{value:5,label:"COLREAR"},{value:6,label:"JKREAR"},{value:7,label:"NETPROB"},{value:8,label:"OPERATOR"},{value:9,label:"TAPBOX"}],n=[{code:0,value:"UNKNOWN_TYPE"},{code:1,value:"X86_LEARN_TYPE"},{code:2,value:"X86_SIGNAL_TYPE"},{code:3,value:"X86_3G_TYPE"},{code:4,value:"SINGLE_NPS_TYPE"},{code:5,value:"DOUBLE_NPS_TYPE"},{code:6,value:"IO_PURELY_TYPE"},{code:7,value:"X86_ADVANCED_TYPE "},{code:8,value:"SWITCH_TYPE "},{code:9,value:"X86_LABEL_TYPE "}],u=[{code:1,label:"ATCA 2槽机箱",value:"ATCA_CHASIS_SLOT2"},{code:2,label:"ATCA 6槽机箱",value:"ATCA_CHASIS_SLOT6"},{code:3,label:"ATCA 14槽机箱",value:"ATCA_CHASIS_SLOT14"},{code:4,label:"CROSS 2槽机箱",value:"CROSS_CHASIS_SLOT2"},{code:5,label:"CROSS 3槽机箱",value:"CROSS_CHASIS_SLOT3"},{code:6,label:"CROSS 8槽机箱",value:"CROSS_CHASIS_SLOT8"},{code:7,label:"CROSS 16槽机箱",value:"CROSS_CHASIS_SLOT16"},{code:8,label:"盒式设备",value:"BOX_CHASIS_SLOT1"},{code:255,value:"CHASIS_UNKNOW"}]}}]);