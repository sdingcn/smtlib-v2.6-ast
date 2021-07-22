package io.github.shuoding.ast;

public class ASTOtherCommand extends ASTCommand {

	public String text;

	public ASTOtherCommand(String text) {
		this.text = text;
	}

	@Override
	public String print() {
		return text;
	}
}
