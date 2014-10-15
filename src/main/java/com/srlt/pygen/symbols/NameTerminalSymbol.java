package com.srlt.pygen.symbols;

import com.pygen.symbols.TerminalSymbol;

/**
 * 
 * @author somerandmlogin <somerandomlogin@gmail.com>
 */
public class NameTerminalSymbol extends TerminalSymbol {
	public NameTerminalSymbol(String name) {
		super(name);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof NameTerminalSymbol))
			return false;
		NameTerminalSymbol otherMyClass = (NameTerminalSymbol) other;
		return this.getName().equals(otherMyClass.getName());
	}
	/*
	 * @Override public String getGeneratorName() { return
	 * RandomString.randomString(3,7); }
	 */
}