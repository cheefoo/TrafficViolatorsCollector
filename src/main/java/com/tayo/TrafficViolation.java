package com.tayo;

/**
 * Created by temitayo on 6/14/17.
 */
public class TrafficViolation
{
    public static final String DELIMITER = "|";
    private String dateofstop;
    private String timeofstop;
    private String agency;
    private String subagency;
    private String description;
    private String location;
    private String latitude;
    private String longtitude;
    private String accident;
    private String belts;
    private String personalinjury;
    private String propertydamage;
    private String fatal;
    private String commlicense;
    private String hazmat;
    private String commvehicle;
    private String alcohol;
    private String workzone;
    private String state;
    private String veichletype;
    private String year;
    private String make;
    private String model;
    private String color;
    private String violationType;
    private String charge;
    private String article;
    private String contributed;
    private String race;
    private String gender;
    private String drivercity;
    private String driverstate;
    private String dlstate;
    private String arresttype;
    private String geolocation;

    public TrafficViolation(String dateofstop, String timeofstop, String agency, String subagency,
                            String description, String location, String latitude, String longtitude, String accident,
                            String belts, String personalinjury, String propertydamage, String fatal, String commlicense,
                            String hazmat, String commvehicle, String alcohol, String workzone, String state,
                            String veichletype, String year, String make, String model, String color, String violationType
            ,String charge, String article, String contributed, String race, String gender,
                            String drivercity, String driverstate, String dlstate, String arresttype, String geolocation)
    {
        this.dateofstop = dateofstop;
        this.timeofstop = timeofstop;
        this.agency = agency;
        this.subagency = subagency;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.accident = accident;
        this.belts = belts;
        this.personalinjury = personalinjury;
        this.propertydamage = propertydamage;
        this.fatal = fatal;
        this.commlicense = commlicense;
        this.hazmat = hazmat;
        this.commvehicle = commvehicle;
        this.alcohol = alcohol;
        this.workzone = workzone;
        this.state = state;
        this.veichletype = veichletype;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.violationType = violationType;
        this.charge = charge;
        this.article = article;
        this.contributed = contributed;
        this.race = race;
        this.gender = gender;
        this.drivercity = drivercity;
        this.driverstate = driverstate;
        this.dlstate = dlstate;
        this.arresttype = arresttype;
        this.geolocation = geolocation;
    }

    @Override
    public String toString()
    {
        return dateofstop + DELIMITER + timeofstop + DELIMITER  + agency + DELIMITER + subagency + DELIMITER +
                description + DELIMITER + location + DELIMITER +  latitude + DELIMITER + longtitude + DELIMITER +
                accident + DELIMITER +  belts + DELIMITER + personalinjury + DELIMITER + propertydamage +
                DELIMITER  + fatal + DELIMITER + commlicense + DELIMITER + hazmat + DELIMITER + commvehicle + DELIMITER +
                alcohol + DELIMITER + workzone + DELIMITER   + state + DELIMITER +  veichletype + DELIMITER + year + DELIMITER +
                make + DELIMITER + model + DELIMITER +  color + DELIMITER + violationType + DELIMITER + charge + DELIMITER +
                article + DELIMITER + contributed + DELIMITER + race + DELIMITER + gender + DELIMITER +  drivercity + DELIMITER +
                driverstate + DELIMITER + dlstate + DELIMITER + arresttype + DELIMITER + geolocation +"\n";
    }

    public static void main(String[] args)
    {
        TrafficViolation vio = new TrafficViolation("09/24/2013", "17:11:00", "MCP", "3rd district, Silver Spring" ,
                "DRIVING VEHICLE ON HIGHWAY WITH SUSPENDED REGISTRATION",
                "8804 FLOWER AVE" , " ",  " ", "No", "No",  "No", "No", "No", "No", "No", "No", "No", "No","MD",
                "02 - Automobile",
                "2008",
                "FORD",
                "4S",
                "BLACK",
                "Citation",
                "13-401(h)",
                "Transportation Article",
                "No",
                "BLACK",
                "M",
                "TAKOMA PARK",
                "MD",
                "MD",
                "A - Marked Patrol", "(38.9835782, -77.09310515)"
        ) ;
        for(int i=0; i<10; i++)
            System.out.println(vio.toString());

    }
}
