package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTAssertCommand extends ASTCommand {

	public ASTTerm term;

	public ASTAssertCommand(ASTTerm term) {
		this.term = term;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append("assert ");
		sb.append(term.print());
		sb.append(")");
		return sb.toString();
	}
}
