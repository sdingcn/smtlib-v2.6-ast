package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTDeclFunCommand extends ASTCommand {

	public String symbol;
	public ArrayList<String> parameterSortList;
	public String returnSort;

	public ASTDeclFunCommand(String symbol, ArrayList<String> parameterSortList, String returnSort) {
		this.symbol = symbol;
		this.parameterSortList = parameterSortList;
		this.returnSort = returnSort;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append("declare-fun ");
		sb.append(symbol);
		sb.append(" (");
		boolean first = true;
		for (String parameterSort : parameterSortList) {
			if (first) {
				sb.append(parameterSort);
				first = false;
			} else {
				sb.append(" ");
				sb.append(parameterSort);
			}
		}
		sb.append(") ");
		sb.append(returnSort);
		sb.append(")");
		return sb.toString();
	}
}
