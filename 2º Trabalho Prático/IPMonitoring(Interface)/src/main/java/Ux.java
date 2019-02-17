
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Ux {
    private int op;
    private Scanner input;
    private static SNMPClient client;
    private static File file = new File("results.html");
    private static FileWriter writer;
    private static Integer id = 0;
    private static Integer id_aux = 1;
    private static HashMap<Integer,Double> diferencas = new HashMap<Integer, Double>();



    public Ux(){
        op=9;
        input = new Scanner(System.in);
        client = new SNMPClient();
    }
    public static void main(String[] args) {
        Ux u = new Ux();



        try {
            if (file.createNewFile())
            {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

            //Write Content
            writer = new FileWriter(file);
            writer.write("" +
                    "<html>\n" +
                    "\t<head>" +
                    "\t\t<meta charset=\"utf-8\">" +
                    "\t</head>" +
                    "\t<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">" +
                    "\t<title>GR - Grupo 3</title>" +
                    "\t<body>\n" +
                    "\t\t<div class = \"w3-container\">" +
                    "\t\t\t<h3>Resultados</h3>\n" +
                    "\t\t\t<hr>" +
                    "\t\t\t<table class = \"w3-table-all w3-centered w3-hoverable\">" +
                    "\t\t\t\t<tr><th>Descrição da Interface</th><th>InOctets</th><th>OutOctets</th><th>Diferença</th></tr>");



            while(u.op!=0){
                id = 1;
                u.displayMenu();
                u.changeOption();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu(){
        System.out.println("=====SNMP MANAGER=====");
        System.out.println("===Select operation===");
        System.out.println("1: Find Interfaces");
        System.out.println("2: Start Monitoring");
        System.out.println("3: Config Client");
        System.out.println("4: Display Traffic");
        System.out.println("0: Exit");
        System.out.println("======================");
        op = parseInt( input.nextLine() );
    }

    private void changeOption(){
        switch (op) {
            case 0:
                client.killAll();

                try {

                    Collection<List<Par>> a = client.getIfTrafficLog().values();
                    ArrayList<String> res = client.interfacesToString();

                    writer.write("" +
                            "\t\t\t</table>" +
                            "\t\t</div>" +
                            "\t\t<br><br><br>" +
                            "\t\t<div class = \"w3-container\">\n" +
                            "\t\t\t<h3>Gráfico de Desenvolvimento</h3>\n" +
                            "\t\t\t<div id=\"chartContainer\" style=\"height: 300px; width: 100%;\">" +
                            "\t\t\t<hr>" +
                            "\t\t</div>" +
                            "<script type=\"text/javascript\">\n" +
                                    "\t\t\t  window.onload = function () {\n" +
                                    "\t\t\t    var chart = new CanvasJS.Chart(\"chartContainer\",\n" +
                                    "\t\t\t    {\n" +
                                    "\n" +
                                    "\t\t\t      title:{\n" +
                                    "\t\t\t      text: \"Monitorização (Comparação)\"\n" +
                                    "\t\t\t      },\n" +
                                    "\t\t\t      data: [\n" +
                                    "\t\t\t      {\n" +
                                    "\t\t\t        type: \"line\",\n" +
                                    "\n" +
                                    "\t\t\t        dataPoints: [\n" );

                    for(int id_dif: diferencas.keySet()) {
                        writer.write("{x:"+id_dif+", y:"+ diferencas.get(id_dif)+" },");
                    }
                    writer.write("\t\t\t        ]\n" +
                                    "\t\t\t      }\n" +
                                    "\t\t\t      ]\n" +
                                    "\t\t\t    });\n" +
                                    "\n" +
                                    "\t\t\t    chart.render();\n" +
                                    "\t\t\t  }\n" +
                                    "  \t\t</script>\n" +
                                    " \t\t<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>" +
                            "\t</body>\n" +
                            "</html>"
                    );
                    writer.close();

                    File htmlFile = new File("results.html");
                    Desktop.getDesktop().browse(htmlFile.toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case 1: {
                try{
                    client.start();
                } catch (Exception e){
                    e.printStackTrace();
                }

                client.fillIfTable();
                break;
            }
            case 2: {
                client.startMonitoring();
                break;
            }
            case 3: {
                client.killAll();
                setupClient();
                break;
            }
            case 4: {
                Collection<List<Par>> a = client.getIfTrafficLog().values();
                ArrayList<String> res = client.interfacesToString();
                int i = 0;
                for(List<Par> l: a) {
                    printInterfaces(l, res.get(i));
                    i++;
                }
                break;
            }
        }
    }

    private void setupClient(){
        String add, ip, port;
        System.out.println("Select IP");
        ip = input.nextLine();
        System.out.println("Select Port");
        port = input.nextLine();
        add = ip + "/" + port;
        client = new SNMPClient(add);
    }

    public void printInterfaces(List<Par> l, String s) {



            try {
                    for( Par x : l) {
                            writer.write("\t\t\t\t<tr>" +
                                    "\t\t\t\t\t<td>" + s + "</td>" +
                                    "\t\t\t\t\t<td>" + x.in + "</td>" +
                                    "\t\t\t\t\t<td>" + x.out + "</td>" +
                                    "\t\t\t\t\t<td>" + x.dif + "</td>" +
                                    "\t\t\t\t</tr>");
                            if(!s.contentEquals("")) {
                                diferencas.put(id, x.dif);
                                id++;
                            }

                    }


            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}

