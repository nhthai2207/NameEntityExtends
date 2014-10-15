package thomas.extendclass.core;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import thomas.extendclass.model.NameEntity;

import com.srlt.pygen.symbols.NameTerminalSymbol;

public class NameDepot {
	private List<NameEntity> nameEntityList;
	private Set<NameTerminalSymbol> symbolSet;

	public NameDepot(List<NameEntity> nameEntityList) {
		this.nameEntityList = nameEntityList;
		this.symbolSet = new TreeSet<NameTerminalSymbol>();
		for (NameEntity nameEntity : this.nameEntityList) {
			this.symbolSet.add(new NameTerminalSymbol(nameEntity.getName()));
		}
		System.out.println("Size of entity: " + this.nameEntityList.size());
		System.out.println("Size of symbol: " + this.symbolSet.size());
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