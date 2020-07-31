package com.coronavirusnotice.covidextractor;

public class COVIDFilter {

    /*
     * Removes all non-residential records
     */
    public COVIDList filterNonResidential(COVIDList list)
    {
        COVIDList filteredList = new COVIDList();

        for(COVIDInstance instance : list)
        {
            if(!instance.getName().endsWith("(non-residential)"))
            {
                filteredList.add(instance);
            }
        }

        return filteredList;
    }

    public COVIDList filterByDistrict(COVIDList list, String district)
    {
        COVIDList filteredList = new COVIDList();

        for(COVIDInstance instance : list)
        {
            if(instance.getDistrict().contains(district))
            {
                filteredList.add(instance);
            }
        }

        return filteredList;
    }

    public COVIDList filterByEstate(COVIDList list, String estate)
    {
        COVIDList filteredList = new COVIDList();

        for(COVIDInstance instance : list)
        {
            if(instance.getName().contains(estate))
            {
                filteredList.add(instance);
            }
        }

        return filteredList;
    }

    public COVIDList filterByBuilding(COVIDList list, String building)
    {
        COVIDList filteredList = new COVIDList();

        for(COVIDInstance instance : list)
        {
            if(instance.getName().contains(building))
            {
                filteredList.add(instance);
            }
        }

        return filteredList;
    }

    public COVIDList filterByBlock(COVIDList list, String block)
    {
        COVIDList filteredList = new COVIDList();

        for(COVIDInstance instance : list)
        {
            if(instance.getName().contains(block))
            {
                filteredList.add(instance);
            }
        }

        return filteredList;
    }
}
