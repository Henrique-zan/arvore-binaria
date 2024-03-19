/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arvore;
/**
 *
 * @author dados
 */
import java.util.Scanner;

class No {
    int chave;
    No esquerda, direita;

    public No(int item) {
        chave = item;
        esquerda = direita = null;
    }
}

class Arvore {
    No raiz;

    Arvore() {
        raiz = null;
    }

    void inserir(int chave) {
        raiz = inserirRec(raiz, chave);
    }

    No inserirRec(No raiz, int chave) {
        if (raiz == null) {
            raiz = new No(chave);
            return raiz;
        }

        if (chave < raiz.chave) {
            raiz.esquerda = inserirRec(raiz.esquerda, chave);
        } else if (chave > raiz.chave) {
            raiz.direita = inserirRec(raiz.direita, chave);
        }

        return raiz;
    }

    void percorrerPreordem(No raiz) {
        if (raiz != null) {
            System.out.print(raiz.chave + " ");
            percorrerPreordem(raiz.esquerda);
            percorrerPreordem(raiz.direita);
        }
    }
    
    void percorrerInordem(No raiz) {
        if (raiz != null) {
            percorrerInordem(raiz.esquerda);
            System.out.print(raiz.chave + " ");
            percorrerInordem(raiz.direita);
        }
    }

    void percorrerPosordem(No raiz) {
        if (raiz != null) {
            percorrerPosordem(raiz.esquerda);
            percorrerPosordem(raiz.direita);
            System.out.print(raiz.chave + " ");
        }
    }

    No encontrarMaior(No raiz) {
        No atual = raiz;
        while (atual.direita != null) {
            atual = atual.direita;
        }
        return atual;
    }

    No removerMaior(No raiz) {
        if (raiz == null) {
            return raiz;
        }

        No maior = encontrarMaior(raiz);

        if (maior.esquerda == null && maior.direita == null) {
            return null;
        }

        if (maior.direita == null) {
            return maior.esquerda;
        }

        maior.direita = removerMaior(maior.direita);

        return maior;
    }

    int removerMaiorERetornarValor() {
        No antes = null;
        No atual = raiz;

        while (atual.direita != null) {
            antes = atual;
            atual = atual.direita;
        }

        int valorRemovido = atual.chave;

        if (antes != null) {
            antes.direita = atual.esquerda;
        } else {
            raiz = atual.esquerda;
        }

        return valorRemovido;
    }

    No encontrarMenor(No raiz) {
        No atual = raiz;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    No removerMenor(No raiz) {
        if (raiz == null) {
            return raiz;
        }

        No menor = encontrarMenor(raiz);

        if (menor.esquerda == null && menor.direita == null) {
            return null;
        }

        if (menor.esquerda == null) {
            return menor.direita;
        }

        menor.esquerda = removerMenor(menor.esquerda);

        return menor;
    }

    int removerMenorERetornarValor() {
        No antes = null;
        No atual = raiz;

        while (atual.esquerda != null) {
            antes = atual;
            atual = atual.esquerda;
        }

        int valorRemovido = atual.chave;

        if (antes != null) {
            antes.esquerda = atual.direita;
        } else {
            raiz = atual.direita;
        }

        return valorRemovido;
    }

    No removerElemento(No raiz, int chave) {
        if (raiz == null) {
            return raiz;
        }

        if (chave < raiz.chave) {
            raiz.esquerda = removerElemento(raiz.esquerda, chave);
        } else if (chave > raiz.chave) {
            raiz.direita = removerElemento(raiz.direita, chave);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }

            raiz.chave = encontrarMenor(raiz.direita).chave;

            raiz.direita = removerElemento(raiz.direita, raiz.chave);
        }

        return raiz;
    }

    void removerElemento(int chave) {
        raiz = removerElemento(raiz, chave);
    }

    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(70);
        arvore.inserir(60);
        arvore.inserir(80);

        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("---------------------------------------------");
            System.out.println("Escolha como percorrer:");
            System.out.println("1 - Pre-ordem");
            System.out.println("2 - Inordem");
            System.out.println("3 - Pos-ordem");
            System.out.println("4 - Remover maior elemento");
            System.out.println("5 - Remover menor elemento");
            System.out.println("6 - Remover elemento N");
            System.out.println("0 - Sair");
            System.out.println("---------------------------------------------");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    
                    System.out.println("modo Pre-ordem:");
                    arvore.percorrerPreordem(arvore.raiz );
                    break;
                case 2:
                    System.out.println("modo Inordem:");
                    arvore.percorrerInordem(arvore.raiz );
                    break;
                case 3:
                    System.out.println("modo Pos-ordem:");
                    arvore.percorrerPosordem(arvore.raiz );
                    break;
                case 4:
                    int valorRemovidoMaior = arvore.removerMaiorERetornarValor();
                    System.out.println("Removido o maior elemento: " + valorRemovidoMaior );
                    System.out.println("Arvore apos a remocao:");
                    arvore.percorrerInordem(arvore.raiz);
                    break;
                case 5:
                    int valorRemovidoMenor = arvore.removerMenorERetornarValor();
                    System.out.println("Removido o menor elemento: " + valorRemovidoMenor );
                    System.out.println("Arvore apos a remocao:");
                    arvore.percorrerInordem(arvore.raiz);
                    break;
                case 6:
                    System.out.println("Digite N paraa ser removido:");
                    int valorRemover = scanner.nextInt();
                    arvore.removerElemento(valorRemover);
                    System.out.println("Arvore apos a remocao de N " + valorRemover + ":" );
                    arvore.percorrerInordem(arvore.raiz);
                    break;
                case 0:
                    System.out.println("Saindo." );
                    break; 
                    
            }
        } while (escolha != 0);

        scanner.close();
    }
}
