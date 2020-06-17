package com.example.babyinformation;

public class DoctorsDatabase {

    private String specialty;

    private String address;

    private String rate;

    private String phone;

    private String latitude;

    private String name;

    private String mobile;

    private String photo;

    private String id;

    private String email;

    private String longitude;

    public String getSpecialty ()
    {
        return specialty;
    }

    public void setSpecialty (String specialty)
    {
        this.specialty = specialty;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getRate ()
    {
        return rate;
    }

    public void setRate (String rate)
    {
        this.rate = rate;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [specialty = "+specialty+", address = "+address+", rate = "+rate+", phone = "+phone+", latitude = "+latitude+", name = "+name+", mobile = "+mobile+", photo = "+photo+", id = "+id+", email = "+email+", longitude = "+longitude+"]";
    }
}
