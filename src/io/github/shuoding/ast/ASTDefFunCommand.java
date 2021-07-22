package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTDefFunCommand extends ASTCommand {

	public String symbol;
	public ArrayList<String> parameterSymbolList;
	public ArrayList<String> parameterSortList;
	public String sort;
	public ASTTerm term;

	public ASTDefFunCommand(String symbol, ArrayList<String> parameterSymbolList,
			ArrayList<String> parameterSortList,
			String sort, ASTTerm term) {
		this.symbol = symbol;
		this.parameterSymbolList = parameterSymbolList;
		this.parameterSortList = parameterSortList;
		this.sort = sort;
		this.term = term;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append("define-fun ");
		sb.append(symbol);
		sb.append(" (");
		int len = parameterSymbolList.size();
		boolean first = true;
		for (int i = 0; i < len; i++) {
			if (first) {
				sb.append("(");
				sb.append(parameterSymbolList.get(i));
				sb.append(" ");
				sb.append(parameterSortList.get(i));
				sb.append(")");
				first = false;
			} else {
				sb.append(" ");
				sb.append("(");
				sb.append(parameterSymbolList.get(i));
				sb.append(" ");
				sb.append(parameterSortList.get(i));
				sb.append(")");
			}
		}
		sb.append(") ");
		sb.append(sort);
		sb.append(" ");
		sb.append(term.print());
		sb.append(")");
		return sb.toString();
	}
}
