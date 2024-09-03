package com.hostmdy.style.model;

public class Style {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private String category;
    private Long category_id;
    private String youtubeLink;
    private Boolean liked;

    

    // Constructor for creating a new style



    // Constructor for retrieving an existing style from the database
    public Style(Long id, String name, String description, String image_url, String category, Long category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.category = category;
        this.category_id = category_id;
    }
    
 

	public Style(String name, String description, String image_url, String category, Long category_id) {
		super();
		this.name = name;
		this.description = description;
		this.image_url = image_url;
		this.category = category;
		this.category_id = category_id;
	}
	
	



	public Style(Long id, String name, String description, String image_url, String category, Long category_id,
			String youtubeLink) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image_url = image_url;
		this.category = category;
		this.category_id = category_id;
		this.youtubeLink = youtubeLink;
	}



	public Style(String name, String description, String image_url, String category, Long category_id,
			String youtubeLink) {
		super();
		this.name = name;
		this.description = description;
		this.image_url = image_url;
		this.category = category;
		this.category_id = category_id;
		this.youtubeLink = youtubeLink;
	}

	
	



	public Style(Long id, String name, String image_url, String category) {
		super();
		this.id = id;
		this.name = name;
		this.image_url = image_url;
		this.category = category;
	}



	// Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}



	public Boolean getLiked() {
		return liked;
	}



	public void setLiked(Boolean liked) {
		this.liked = liked;
	}
	
	
    
    
}


