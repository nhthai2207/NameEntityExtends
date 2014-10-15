package thomas.extendclass.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import thomas.extendclass.model.NameEntity;

import com.srlt.pygen.symbols.NameTerminalSymbol;

public class NameDepot {
	private List<NameEntity> nameEntityList;
	private Set<NameTerminalSymbol> symbolSet;

	public NameDepot(List<NameEntity> nameEntityList) {
		this.nameEntityList = nameEntityList;
		this.symbolSet = new HashSet<NameTerminalSymbol>();
		for (NameEntity nameEntity : this.nameEntityList) {
			this.symbolSet.add(new NameTerminalSymbol(nameEntity.getName()));
		}
	}

	public List<NameEntity> getNameEntityList() {
		return nameEntityList;
	}

	public void setNameEntityList(List<NameEntity> nameEntityList) {
		this.nameEntityList = nameEntityList;
	}

	public Set<NameTerminalSymbol> getSymbolSet() {
		return symbolSet;
	}

	public void setSymbolSet(Set<NameTerminalSymbol> symbolSet) {
		this.symbolSet = symbolSet;
	}
}