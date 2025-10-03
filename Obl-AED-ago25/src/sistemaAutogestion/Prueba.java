package sistemaAutogestion;

public class Prueba {

    public static void main(String[] args) {
        Sistema s = new Sistema();
        s.crearSistemaDeGestion();  // important to initialize the lists

        //int mat[][] = {{1, 5, 6}, {1, 1, 1}, {8, 2, 1}};
        String mapa[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "o", "o", "o", "E7", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        
        informaciónMapa(mapa);
    }

    public static void informaciónMapa(String[][] mat) {
        int filaMax = 0;
        int maxColumna = 0;

        //Cuento estaciones por Fila
        for (int i = 0; i < mat.length; i++) {
            int countFila = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (!mat[i][j].equals("o")) {
                    countFila++;
                }
            }

            if (countFila > filaMax) {
                filaMax = countFila;
            }
        }

        //Cuento estaciones por Columna
        for (int j = 0; j < mat[0].length; j++) {
            int countCol = 0;
            for (int i = 0; i < mat.length; i++) {
                if (!mat[i][j].equals("o")) {
                    countCol++;
                }
            }
            if (countCol > maxColumna) {
                maxColumna = countCol;
            }
        }

        String resultadoParcial;
        if (filaMax == 0 && maxColumna == 0) {
            resultadoParcial = "0#ambas";
        } else if (filaMax > maxColumna) {
            resultadoParcial = filaMax + "#fila";
        } else if (maxColumna > filaMax) {
            resultadoParcial = maxColumna + "#columna";
        } else {
            resultadoParcial = filaMax + "#ambas";
        }
        
        System.out.println(resultadoParcial);

    }
}
