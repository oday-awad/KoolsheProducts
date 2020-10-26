package net.rebi.koolsheProducts.Calsses;


public class Category1 {
    private String   name;
    private String   parent;
    private String sectionsNames ;


    public Category1 ( String name , String parent ,String sectionsNames ) {
        this.name = name;
        this.parent = parent;
        this.sectionsNames = sectionsNames;
    }


    public String getName ( ) {
        return name;
    }


    public String getParent ( ) {
        return parent;
    }

    public String getSectionsNames ( ) {
        return sectionsNames;
    }


}
