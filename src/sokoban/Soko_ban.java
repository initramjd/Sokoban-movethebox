package sokoban;

import java.util.Scanner;

public class Soko_ban {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);

		String[] board = { "O", " ", " ", " ", " ", " ", "B", "X", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ",
				" ", " ", " ", " ", " ", " ", "E" };
		int[] arrayW = { 0, 1, 2, 3, 4 };
		int[] arrayS = { 20, 21, 22, 23, 24 };
		int[] arrayA = { 0, 5, 10, 15, 20 };
		int[] arrayD = { 4, 9, 14, 19, 24 };

		String enterMove = " ";
		String b = "B";
		String o = "O";
		int positionO = 0;
		int positionB = findB(board);
		int newMove = 0;

		printBoard(board);

		while (!isFinishGame(board)) { // pocetak glavne petlje

			System.out.println();
			System.out.print("Unesite W S A D za pomeranje: ");
			enterMove = s.next();
			newMove = EnterMove(enterMove);

			if ((checkMove(newMove, positionO) && roundMove(positionO, newMove, arrayW, arrayS, arrayA, arrayD))) { // provera
																													// validnosti
																													// kretanja
																													// igraca

				if (!isOverX(board, positionO, newMove)) { // provera da li igrac ne udara u zid

					if (!isOverB(board, positionO, newMove)) { // da li igrac ne gura kocku
						newBoard(board, positionO, newMove, o);
						positionO = positionO + newMove;

						// kraj provere da li igrac ne gura kocku
					} else { // ako igrac ne gura kocku

						if ((checkMove(newMove, positionB) // provera da li ce kocka ispasti sa table
								&& roundMove(positionB, newMove, arrayW, arrayS, arrayA, arrayD))) {

							if (!isOverX(board, positionB, newMove)) { // pocetak provere da li kocka udara u zid nakon
																		// pomeranja od strane igraca

								newBoard(board, positionB, newMove, b);
								positionB = positionB + newMove;
								newBoard(board, positionO, newMove, o);
								positionO = positionO + newMove;

							} else { // pomeranje igraca i kocke
								System.out.println();
								System.out.println("Kocki smeta zid");

							} // kraj poceranja igraca i kocke

						} else {
							System.out.println();
							System.out.println("Kutija ce ispasti sa table");
						} //
					} // kraj ako igrac ne gura kocku

				} else { // kraj provere da li igrac ne udara u zid
					System.out.println();
					System.out.println("Smeta ti zid");
				}

			} else { // kraj provere kretanja igraca
				System.out.println();
				System.out.println("Ne mozes van table");

			}

			if (!isFinishGame(board)) {
				returnE(board);
			}
			printBoard(board);
		} // kraj glavne petlje
		System.out.println();
		System.out.println("BRAVO, KRAJ IGRE !!!");

	}

	public static void returnE(String[] board) {
		if (!board[24].equals("O")) {
			board[24] = "E";
		}

	}

	public static void printBoard(String[] board) {

		for (int i = 0; i < board.length; i++) {

			System.out.print(board[i] + "| ");
			if ((i + 1) % 5 == 0) {

				System.out.println();
			}
		}

	}

	public static int EnterMove(String enterMove) {
		int newMove = 0;

		switch (enterMove) {
		case "W":
			newMove = -5;
			break;

		case "S":
			newMove = 5;
			break;

		case "A":
			newMove = -1;
			break;

		case "D":
			newMove = 1;
			break;

		}

		return newMove;
	}

	public static boolean isFinishGame(String[] board) {

		return (findB(board) == 24);

	}

	public static int findB(String[] board) {
		int whereIsB = 0;
		for (int i = 0; i < board.length; i++) {

			if (board[i].equals("B")) {

				whereIsB = i;
			}
		}

		return whereIsB;
	}

	public static boolean checkMove(int newMove, int postition) {

		return (postition + newMove >= 0 && postition + newMove < 25);
	}

	public static void newBoard(String[] board, int position, int newMove, String OB) {

		for (int i = 0; i < board.length; i++) {

			if (board[i].equals(OB)) {

				board[i] = " ";
				board[position + newMove] = OB;
			}
		}

	}

	public static boolean roundMove(int position, int newMove, int[] arrayW, int[] arrayS, int[] arrayA, int[] arrayD) {

		switch (newMove) {
		case -5:

			if (roundMoveCheck(arrayW, position)) {

				return true;
			}

			break;

		case 5:

			if (roundMoveCheck(arrayS, position)) {

				return true;
			}
			break;

		case -1:

			if (roundMoveCheck(arrayA, position)) {

				return true;
			}
			break;

		case 1:

			if (roundMoveCheck(arrayD, position)) {

				return true;
			}
			break;

		}

		return false;
	}

	public static boolean roundMoveCheck(int[] arrayWSAD, int position) {

		for (int i = 0; i < arrayWSAD.length; i++) {

			if (position == arrayWSAD[i]) {

				return false;

			}
		}
		return true;
	}

	public static boolean isOverX(String[] board, int position, int newMove) {

		if (board[position + newMove].equals("X")) {
			return true;
		}

		return false;
	}

	public static boolean isOverB(String[] board, int positionO, int newMove) {

		if (board[positionO + newMove].equals("B")) {
			return true;
		}

		return false;

	}

}
