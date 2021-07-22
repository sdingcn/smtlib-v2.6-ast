package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTLetTerm extends ASTTerm {

	public ArrayList<String> symbolList;
	public ArrayList<ASTTerm> termList;
	public ASTTerm term;

	public ASTLetTerm(ArrayList<String> symbolList, ArrayList<ASTTerm> termList, ASTTerm term) {
		this.symbolList = symbolList;
		this.termList = termList;
		this.term = term;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(let (");
		int len = symbolList.size();
		boolean first = true;
		for (int i = 0; i < len; i++) {
			if (first) {
				sb.append("(");
				sb.append(symbolList.get(i));
				sb.append(" ");
				sb.append(termList.get(i).print());
				sb.append(")");
				first = false;
			} else {
				sb.append("(");
				sb.append(symbolList.get(i));
				sb.append(" ");
				sb.append(termList.get(i).print());
				sb.append(")");
			}
		}
		sb.append(") ");
		sb.append(term.print());
		sb.append(")");
		return sb.toString();
	}
}
