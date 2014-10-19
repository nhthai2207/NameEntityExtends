package thomas.extendclass.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import thomas.extendclass.model.ArgEntity;
import thomas.extendclass.model.FuncEntity;
import thomas.extendclass.model.NameEntity;
import thomas.extendclass.utils.CommonUtils;

import com.srlt.pygen.symbols.NameTerminalSymbol;

public class NameDepot {
	private static enum Categories {tfpdef, funcdef, classdef};
	private List<NameEntity> nameEntityList;
	private Set<NameTerminalSymbol> symbolSet;
	private Map<String, List<NameEntity>> categories;

	public NameDepot(List<NameEntity> nameEntityList) {
		this.nameEntityList = nameEntityList;
		this.symbolSet = new TreeSet<NameTerminalSymbol>();
		for (NameEntity nameEntity : this.nameEntityList) {
			this.symbolSet.add(new NameTerminalSymbol(nameEntity.getName()));
		}
		this.buildCategories();		
	}
	
	public void output(){
		System.out.println("Size of entity: " + this.nameEntityList.size());
		System.out.println("Size of symbol: " + this.symbolSet.size());	
		for(String key : this.categories.keySet()){
			System.out.println("Size of categories: " + key + " = " + this.categories.get(key).size());
		}
	}

	private void buildCategories(){
		this.categories = new HashMap<String, List<NameEntity>>();
		this.categories.put(Categories.tfpdef.name(), new ArrayList<NameEntity>());
		this.categories.put(Categories.funcdef.name(), new ArrayList<NameEntity>());
		this.categories.put(Categories.classdef.name(), new ArrayList<NameEntity>());
		for(NameEntity nameEntity : nameEntityList){
			if(nameEntity instanceof ArgEntity ){
				this.categories.get(Categories.tfpdef.name()).add(nameEntity);
			}
			if(nameEntity instanceof FuncEntity){				
				this.categories.get(Categories.funcdef.name()).add(nameEntity);
				FuncEntity tmp = (FuncEntity) nameEntity;
				if(!CommonUtils.isEmptyString(tmp.getClazz())){
					this.categories.get(Categories.classdef.name()).add(nameEntity);
				}
			}
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