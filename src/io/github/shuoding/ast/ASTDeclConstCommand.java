package io.github.shuoding.ast;

public class ASTDeclConstCommand extends ASTCommand {

	public String symbol;
	public String sort;

	public ASTDeclConstCommand(String symbol, String sort) {
		this.symbol = symbol;
		this.sort = sort;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append("declare-const ");
		sb.append(symbol);
		sb.append(" ");
		sb.append(sort);
		sb.append(")");
		return sb.toString();
	}
}
