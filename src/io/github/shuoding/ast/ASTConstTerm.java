package io.github.shuoding.ast;

public class ASTConstTerm extends ASTTerm {

	public String spec_constant;

	public ASTConstTerm(String spec_constant) {
		this.spec_constant = spec_constant;
	}

	@Override
	public String print() {
		return spec_constant;
	}
}
