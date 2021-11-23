package com.roinma;
import com.sap.conn.jco.*;
/**
 * Created by roin.zhou on 2021/11/23
 * 连接SAP RFC 并且设置传参和读取返回表
 */
public class Main {

    public static void main(String[] args) throws JCoException {

        SapConn con = new SapConn(
                "10.8.13.152",
                "00",
                "800",
                "xiaoma",
                "ZHOUln997237653",
                "zh",
                "123",
                "321",
                ""
        );

        // 测试连接
        JCoDestination destination = SAPConnUtils.connect(con);
        String userName = destination.getUser();
        String client = destination.getClient();
        JCoFunction function = destination.getRepository().getFunction("Z_TEST_001");
        // 给import参数设置值
        function.getImportParameterList().setValue("I_MATNR", "000000000003000008");
        // 读取import的值
        System.out.println(function.getImportParameterList().getValue("I_MATNR"));
        function.execute(destination);
        //读取返回的表
        JCoTable rTmlist = function.getTableParameterList().getTable("P_OUT");
        System.out.println(rTmlist.getNumRows());
        for (int i = 0; i < rTmlist.getNumRows(); i++) {
            // Sets the row pointer to the specified position(beginning from zero)
            rTmlist.setRow(i);
            // Each line is of type JCoStructure
            for (JCoField fld : rTmlist) {
                System.out.print(String.format("%s\t", fld.getValue()));
            }
            System.out.println();
        }
    }
}
