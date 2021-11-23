package com.roinma;

import com.sap.conn.jco.*;


// import com.roinma.SapConn;
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
        JCoFunction function = destination.getRepository().getFunction("MD_STOCK_REQUIREMENTS_LIST_API");
        function.getImportParameterList().setValue("MATNR", "2000001");
        function.getImportParameterList().setValue("WERKS", 1000);
        function.getImportParameterList().setValue("PLSCN", "000");
        System.out.println(function.getImportParameterList().getValue("MATNR"));
        function.execute(destination);
        JCoTable rTmlist = function.getTableParameterList().getTable("RETURN");
        System.out.println(rTmlist.getNumRows());
        for (JCoField fld : rTmlist) {
            System.out.print(String.format("%s\t", fld.getValue()));
        }
        JCoTable rTmlist2 = function.getTableParameterList().getTable("MATERIALDESCRIPTION");
        System.out.println(rTmlist2.getNumRows());
        for (JCoField fld : rTmlist2) {
            System.out.print(String.format("%s\t", fld.getValue()));
        }


      /*  for (int i = 0; i < rTmlist.getNumRows(); i++) {
            // Sets the row pointer to the specified position(beginning from zero)
            rTmlist.setRow(i);
            // Each line is of type JCoStructure
            for (JCoField fld : rTmlist) {
                System.out.print(String.format("%s\t", fld.getValue()));
                System.out.println();
            }
            System.out.println();*/

        }
    }

