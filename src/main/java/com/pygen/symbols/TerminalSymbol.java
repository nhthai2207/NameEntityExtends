package com.pygen.symbols;


/**
 * 
 * @author thomas.j.zheng <thomas.j.zheng@gmail.com>
 */
public class TerminalSymbol extends NonterminalSymbol {
	protected String value; // can be used to replace random generation

	public TerminalSymbol(String name) {
		super(name);
		value = "";
	}

	/*
	 * @Override public void debugPrint( int indent ) { String ind_str = ""; for
	 * ( int i = 0; i < indent; i++ ) ind_str += " "; Debug.log( String.format(
	 * "%s'%s'%s", ind_str, this.name, this.getPrintQuantifier() ) ); }
	 */
	/*
	 * @Override public String getGeneratorName() { return this.getName() + " ";
	 * }
	 */
}
