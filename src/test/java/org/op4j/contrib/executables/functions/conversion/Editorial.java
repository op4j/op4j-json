package org.op4j.contrib.executables.functions.conversion;


public class Editorial {

	private Integer id;
 	
	private String name;
	private String address;
	
	public Editorial() {
		super();
		
	}

	public Editorial(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Editorial [address=" + this.address + ", id=" + this.id
				+ ", name=" + this.name + "]";
	}	
}
