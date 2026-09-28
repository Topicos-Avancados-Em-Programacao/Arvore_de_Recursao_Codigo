package arvore_de_recursao_correta;

import java.util.Scanner;

public class Arvore_de_Recursao {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

        System.out.println("Recorrência: T(n) = a * T(n/b) + c * n^k");
        System.out.print("a: ");
        int a = sc.nextInt();
        System.out.print("b: ");
        int b = sc.nextInt();
        System.out.print("c: ");
        int c = sc.nextInt();
        System.out.print("k: ");
        int k = sc.nextInt();

        System.out.println("Escreva a quantidade de niveis que vc quer visualizar:");
        int nivel = sc.nextInt();

        String nk = (k == 0) ? "" : (k == 1) ? "n" : "n^" + k;   // n^k em texto
        double logBA = Math.log(a) / Math.log(b);                 // log_b(a)
        int bk = (int) Math.pow(b, k);                            // b^k

        System.out.println("\nT(n) = " + a + "T(n/" + b + ") + " + termo(c, nk));
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-8s| %-10s| %-8s| %-13s| %-15s| %-15s%n",
                "Nível", "Nós", "Tamanho", "Custo/nó", "Custo/nível", "Acumulado");
        System.out.println("------------------------------------------------------------------------");

        double acumulado = 0;
        for (int i = 0; i <= nivel; i++) {
            long nos = (long) Math.pow(a, i);                       // a^i
            String tamanho = (i == 0) ? "n" : "n/" + (long) Math.pow(b, i);
            double custoNo = c / Math.pow(b, i * k);                // c * (n/b^i)^k
            double custoNivel = nos * custoNo;                      // a^i * custo por nó
            acumulado += custoNivel;

            System.out.printf("%-8d| %-10d| %-8s| %-13s| %-15s| %-15s%n",
                    i, nos, tamanho, termo(custoNo, nk), termo(custoNivel, nk), termo(acumulado, nk));
        }

        System.out.printf("%-8s| %-10s| %-8s| %-13s| %-15s|%n",
                "i", a + "^i", "n/" + b + "^i", c + "(n/" + b + "^i)^" + k, a + "^i * custo/nó");
        System.out.printf("%-8s| %-10s| %-8s| %-13s| %-15s|%n",
                "h", "n^" + String.format("%.2f", logBA), "1", "1", "n^" + String.format("%.2f", logBA));
        System.out.println("------------------------------------------------------------------------");

        // Resumo
        System.out.println("\nAltura da árvore : log_" + b + "(n)");
        System.out.println("Total de níveis  : log_" + b + "(n) + 1");
        System.out.printf ("Total de folhas  : n^(log_%d %d) = n^%.4f%n", b, a, logBA);

        String complexidade;
        if (a > bk) {
            complexidade = String.format("Θ(n^%.4f)  -> as folhas dominam", logBA);
        } else if (a == bk) {
            complexidade = "Θ(" + (k == 0 ? "" : nk + " ") + "log n)  -> todos os níveis custam igual";
        } else {
            complexidade = "Θ(" + nk + ")  -> a raiz domina";
        }
        System.out.println("Custo total      : " + complexidade);

        sc.close();
    }

    // Formata coeficiente * n^k  (ex: 4, 2n, 0,44n^2)
    static String termo(double coef, String nk) {
        String num = (coef == (long) coef) ? String.valueOf((long) coef) : String.format("%.2f", coef);
        if (nk.isEmpty()) return num;
        return (num.equals("1") ? "" : num) + nk;

	}

}
