package io.github.shuoding.ast;

public class ASTIDTerm extends ASTTerm {

	public String qual_identifier;

	public ASTIDTerm(String qual_identifier) {
		this.qual_identifier = qual_identifier;
	}

	@Override
	public String print() {
		return qual_identifier;
	}
}
