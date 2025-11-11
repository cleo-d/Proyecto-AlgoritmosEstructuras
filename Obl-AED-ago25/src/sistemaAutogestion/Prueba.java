package sistemaAutogestion;

public class Prueba {

    public static void main(String[] args) {
        Sistema s = new Sistema();
        s.crearSistemaDeGestion();  // important to initialize the lists

        //int mat[][] = {{1, 5, 6}, {1, 1, 1}, {8, 2, 1}};
        String mapa1[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "o", "o", "o", "E7", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        String mapa2[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        String mapa3[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "E7", "o", "o", "o", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };

        informaciónMapa(mapa3);
    }

    public static void informaciónMapa(String[][] mat) {
        int filaMax = 0;
        int maxColumna = 0;
        String resultadoParcial = "";
        int consecutiva = 0;

        //TRABAJO CON LAS FILAS
        for (int i = 0; i < mat.length; i++) {
            int countFila = 0;
            //System.out.println("trabajo con i: " + i);

            //Aca recorro cada posicion dentro de la fila
            for (int j = 0; j < mat[i].length; j++) {
                //System.out.println("trabajo con j: " + j);
                if (!mat[i][j].equals("o")) {
                    countFila++;
                    consecutiva++;
                    //System.out.println("countFila: "+ countFila);
                }
            }

            if (countFila > filaMax) {
                filaMax = countFila;
            }
        }

        //TRABAJO CON LAS COLUMNAS
        int colAnterior = 0;
        int consecutivas = 1;
        boolean existeAscendencia = false;

        for (int j = 0; j < mat[0].length; j++) {
            System.out.println("VOY CON COLUMNA: " + j);
            int countCol = 0;
            for (int i = 0; i < mat.length; i++) {
                if (!mat[i][j].equals("o")) {
                    countCol++;
                }
            }
            System.out.println("TERMINO LA COLUMNA CON # DE ESTACIONES: " + countCol);
            System.out.println("la columna anterior tenia " + colAnterior + " Estaciones");

            //Reviso por ascendencia
            if (colAnterior == countCol - 1) {
                consecutivas++;
                System.out.println("Le sumo +1 a consecutivas");
                System.out.println("consecutivas: " + consecutivas);
                if (consecutivas >= 3) {
                    existeAscendencia = true;
                    System.out.println("EXISTEEE: " + existeAscendencia);
                }
            } else {
                System.out.println("Reseteo consecutivas");
                consecutivas = 1;
            }
            colAnterior = countCol;
            if (countCol > maxColumna) {
                maxColumna = countCol;
            }
        }

        if (filaMax > maxColumna) {
            resultadoParcial += filaMax + "#" + "fila" + "|";
        } else if (maxColumna > filaMax) {
            resultadoParcial += maxColumna + "#" + "columna" + "|";
        } else {
            resultadoParcial += maxColumna + "#" + "ambas" + "|";
        }

        resultadoParcial += existeAscendencia ? "existe" : "no existe";

        System.out.println(resultadoParcial);

    }
}
