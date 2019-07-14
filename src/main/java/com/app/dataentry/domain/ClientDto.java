package com.app.dataentry.domain;

import org.springframework.util.StringUtils;
import com.app.dataentry.model.Client;

public class ClientDto implements BaseDto<Client> {

    private Long id;
	private String name;
    private String mobileNo;
    private String product;

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

    public ClientDto() {

    }
    
    public ClientDto(Client c) {
        mapFromEntity(c);
    }


    @Override
    public ClientDto mapFromEntity(Client entity) {

        id = entity.getId();
        name = entity.getName();
        mobileNo = entity.getMobileNo();
        product = entity.getProduct();
        return this;
    }

    @Override
    public Client mapToEntity() {
        Client entity = new Client();
        if (!StringUtils.isEmpty(id)) {
			entity.setId(id);
        }
        entity.setName(name);
        entity.setMobileNo(mobileNo);
        entity.setProduct(product);
        return entity;
    }
}
