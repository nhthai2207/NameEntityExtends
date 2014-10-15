package com.srlt.pygen.symbols;

import com.pygen.symbols.TerminalSymbol;

/**
 * 
 * @author somerandmlogin <somerandomlogin@gmail.com>
 */
public class NameTerminalSymbol extends TerminalSymbol implements Comparable {
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

	@Override
	public int compareTo(Object other) {

		if (other == null)
			return 1;
		if (other == this)
			return 0;
		if (!(other instanceof NameTerminalSymbol))
			return 1;
		NameTerminalSymbol otherMyClass = (NameTerminalSymbol) other;
		return this.getName().equals(otherMyClass.getName()) ? 0 : 1;

	}
}