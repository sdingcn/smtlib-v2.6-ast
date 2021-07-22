package io.github.shuoding.ast;

public class ASTSetLogicCommand extends ASTCommand {

	public String symbol;

	public ASTSetLogicCommand(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append("set-logic ");
		sb.append(symbol);
		sb.append(")");
		return sb.toString();
	}
}
