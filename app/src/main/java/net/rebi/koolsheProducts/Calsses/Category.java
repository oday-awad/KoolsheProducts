package net.rebi.koolsheProducts.Calsses;


public class Category {
    private String   name;
    private String   categoryTag;
    private String[] sectionsNames ;


    public Category ( String name , String categoryTag ,String[] sectionsNames ) {
        this.name = name;
        this.categoryTag = categoryTag;
        this.sectionsNames = sectionsNames;
    }


    public String getName ( ) {
        return name;
    }


    public String getCategoryTag ( ) {
        return categoryTag;
    }

    public String[] getSectionsNames ( ) {
        return sectionsNames;
    }


}
