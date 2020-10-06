package net.rebi.koolsheProducts.Calsses;

public class Section {
    private String sectionName;
    private String SectionContent;

    public Section ( String sectionName , String sectionContent ) {
        this.sectionName = sectionName;
        SectionContent = sectionContent;
    }

    public String getSectionName ( ) {
        return sectionName;
    }

    public String getSectionContent ( ) {
        return SectionContent;
    }
}
