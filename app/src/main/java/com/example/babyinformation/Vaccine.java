package com.example.babyinformation;

import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Vaccine {
    //this class contains the vaccines that any child should take
    private static class Vaccine_General{
        public String name;
        public int days_post_birth;//this indecates how many days after the birth should that baby take that vaccine
        public static ArrayList<Vaccine_General> vaccines = new ArrayList<>();

        public Vaccine_General(String name, int days_post_birth) {
            this.name = name;
            this.days_post_birth = days_post_birth;
        }

        static {
            vaccines.add(new Vaccine_General("Hepatitis B: 1st dose", 0));
            vaccines.add(new Vaccine_General("Tuberculosis", 0));

            vaccines.add(new Vaccine_General("Hepatitis B: 2nd dose at 1 to 2 months", 60));//2 months
            vaccines.add(new Vaccine_General("Meningococcal: 2 months through 18 years for certain high risk groups", 60));//2 months

            vaccines.add(new Vaccine_General("DTaP (Diphtheria, Tetanus and Pertussis): 1st dose", 60));//4 months
            vaccines.add(new Vaccine_General("Hib (Haemphilus influenzae type b): 1st dose", 60));//6 months
            vaccines.add(new Vaccine_General("Pneumococcal Conjugate (PCV13): 1st dose", 60));//9 months
            vaccines.add(new Vaccine_General("Polio (IPV): 1st dose", 60));//1 year
            vaccines.add(new Vaccine_General("Rotavirus (RV): 1st dose", 60));//1.5 years

            vaccines.add(new Vaccine_General("DTaP (Diphtheria, Tetanus and Pertussis): 2nd dose", 120));
            vaccines.add(new Vaccine_General("Hib (Haemphilus influenzae type b): 2nd dose", 120));
            vaccines.add(new Vaccine_General("Pneumococcal Conjugate )PCV13(: 2nd dose", 120));
            vaccines.add(new Vaccine_General("Polio (IPV): 2nd dose", 120));
            vaccines.add(new Vaccine_General("Rotavirus )RV(: 2nd dose", 120));

            vaccines.add(new Vaccine_General("Influenza: yearly beginning at 6 months", 180));//TODO: set this to yearly

            vaccines.add(new Vaccine_General("Hepatitis B: 3rd dose at 6 to 18 months", 180));
            vaccines.add(new Vaccine_General("Polio (IPV): 3rd dose at 6 to 18 months", 180));

            vaccines.add(new Vaccine_General("DTaP (Diphtheria, Tetanus and Pertussis): 3rd dose", 180));
            vaccines.add(new Vaccine_General("Hib (Haemphilus influenzae type b): 3rd dose depending on Vaccine series", 180));
            vaccines.add(new Vaccine_General("Pneumococcal Conjugate (PCV13): 3rd dose", 180));
            vaccines.add(new Vaccine_General("Rotavirus (RV): if 3 dose Vaccine series", 180));

            vaccines.add(new Vaccine_General("Hib (Haemphilus influenzae type b): 3rd or 4th dose at 12 to 15 months depending on Vaccine series", 365));
            vaccines.add(new Vaccine_General("Pneumococcal Conjugate (PCV13): 4th dose at 12 to 15 months", 365));
            vaccines.add(new Vaccine_General("Measles, Mumps, Rubella )MMR(: 1st dose at 12 to 15 months", 365));
            vaccines.add(new Vaccine_General("Varicella: 1st dose at 12 to 15 months", 365));
            vaccines.add(new Vaccine_General("Hepatitis A: 1st and 2nd dose at 12 to 23 months (given as 2 doses that are 6 months apart)", 365));

            vaccines.add(new Vaccine_General("DTaP (Diphtheria, Tetanus & Pertussis): 4th dose at 15 to 18 months", 450));

            vaccines.add(new Vaccine_General("Pneumococcal polysac-charide (PPSV23): 2 years through 18 years for certain high risk groups", 730));

            vaccines.add(new Vaccine_General("DTaP (Diphtheria, Tetanus and Pertussis(: 5th dose at 4 years to 6 years old", 1460));
            vaccines.add(new Vaccine_General("Polio )IPV(: 4th dose at 4 years to 6 years old", 1460));
            vaccines.add(new Vaccine_General("Measles, Mumps, Rubella )MMR(: 2nd dose at 4 years to 6 years old", 1460));
            vaccines.add(new Vaccine_General("Varicella: 2nd dose at 4 years to 6 years old", 1460));

        }
    }


    public String childName;
    public String vaccName;
    public String vaccDate;

    public Vaccine(String childName, String vaccName, String vaccDate) {
        this.childName = childName;
        this.vaccName = vaccName;
        this.vaccDate = vaccDate;
    }

    //exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 1", "Ahmed", "Measles 1", "12 May"));
    public static ArrayList<Vaccine> getChildrenVaccineList(List<Vaccination_ChildrenData> children) {
        ArrayList<Integer> nxt_vacc_indx = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            int days_passed = getDaysDifference(children.get(i).getBirthdate());
            for (int j = 0; ; j++) {
                if (j >= Vaccine_General.vaccines.size()) {
                    nxt_vacc_indx.add(-1);
                    break;
                } else if (days_passed <= Vaccine_General.vaccines.get(j).days_post_birth) {
                    nxt_vacc_indx.add(j);
                    break;
                }
            }
        }

        ArrayList<Vaccine> vaccinationList = new ArrayList<>();
        int flag = 0;
        while (flag == 0) {
            flag = 1;
            for (int j = 0; j < children.size(); j++) {
                if(nxt_vacc_indx.get(j) == -1)
                    continue;
                flag = 0;

                Vaccination_ChildrenData child = children.get(j);
                while(true) {
                    Vaccine_General vaccine_info = Vaccine_General.vaccines.get(nxt_vacc_indx.get(j));
                    vaccinationList.add(new Vaccine(child.getName(), vaccine_info.name, daysToDate(child.getBirthdate(), vaccine_info.days_post_birth)));

                    int new_indx = nxt_vacc_indx.get(j) + 1;
                    if(new_indx >= Vaccine_General.vaccines.size()) // index out of bound
                    {
                        nxt_vacc_indx.set(j,-1);
                        break;
                    }
                    else{ // there is a next index, maybe the same duration or not
                        nxt_vacc_indx.set(j, new_indx);
                        Vaccine_General new_vaccine_info = Vaccine_General.vaccines.get(nxt_vacc_indx.get(j));

                        //if it is the same duration continue in the loop. else, break;
                        if(vaccine_info.days_post_birth != new_vaccine_info.days_post_birth)
                            break;
                    }
                }
            }
        }
        return vaccinationList;
    }

    private static int getDaysDifference(String BOD) {
        //returns the difference between the current date and the child's birthdate, returns the result in days
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(Calendar.getInstance().getTime());

        int[] BODarr = stringDateToInt(BOD);
        int[] nowArr = stringDateToInt(now);

        ArrayList<Integer> diff = new ArrayList<>();
        for (int i = 0; i < BODarr.length; i++) {
            diff.add(Math.abs(BODarr[i] - nowArr[i]));
        }

        return (diff.get(0) * 365) + (diff.get(1) * 30) + diff.get(2);
    }

    private static int[] stringDateToInt(String stringDate) {
        String[] arrDate = stringDate.split("-");
        int[] intDate = new int[3];
        for (int i = 0; i < arrDate.length; i++) {
            intDate[i] = Integer.parseInt(arrDate[i]);
        }
        return intDate;
    }

    private static String daysToDate(String BOD, int days_num) {
        //add the number of days to the BOD
        int d = days_num % 30;
        days_num = (int) (days_num / 30);
        int mo = days_num % 12;
        int yr = (int) (days_num / 12);

        int[] BODarr = stringDateToInt(BOD);
        yr += BODarr[0];
        mo += BODarr[1];
        d += BODarr[2];
        if (mo > 12) {
            yr += (int) (mo / 12);
            mo = mo % 12;
        }
        if(d > 30){
            mo += (int) (d / 30);
            d = d % 30;
        }

        return (Integer.toString(yr) + "-" + Integer.toString(mo) + "-" + Integer.toString(d));
    }
}
