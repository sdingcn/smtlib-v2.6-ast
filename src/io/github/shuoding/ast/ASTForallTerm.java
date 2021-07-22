package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTForallTerm extends ASTTerm {

	public ArrayList<String> symbolList;
	public ArrayList<String> sortList;
	public ASTTerm term;

	public ASTForallTerm(ArrayList<String> symbolList, ArrayList<String> sortList, ASTTerm term) {
		this.symbolList = symbolList;
		this.sortList = sortList;
		this.term = term;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(forall (");
		int len = symbolList.size();
		boolean first = true;
		for (int i = 0; i < len; i++) {
			if (first) {
				sb.append("(");
				sb.append(symbolList.get(i));
				sb.append(" ");
				sb.append(sortList.get(i));
				sb.append(")");
				first = false;
			} else {
				sb.append("(");
				sb.append(symbolList.get(i));
				sb.append(" ");
				sb.append(sortList.get(i));
				sb.append(")");
			}
		}
		sb.append(") ");
		sb.append(term.print());
		sb.append(")");
		return sb.toString();
	}
}
