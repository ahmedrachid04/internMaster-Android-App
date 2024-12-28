package com.example.projet_stage_mobile_gestion.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.projet_stage_mobile_gestion.DataBase.Models.ApplicationModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.CompanyModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.OfferModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Status;
import com.example.projet_stage_mobile_gestion.DataBase.Models.StudentModel;
import com.example.projet_stage_mobile_gestion.DataBase.Models.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternshipDataBaseHelper extends SQLiteOpenHelper {
    public static final String STUDENT_TABLE = "student";
    public static final String APPLICATION_TABLE = "application";
    public static final String OFFER_TABLE = "offer";
    public static final String COMPANY_TABLE = "company";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String NUMBER = "phone_number";
    public static final String PASSWORD = "password";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESSE = "address";

    public InternshipDataBaseHelper(Context context) {
        super(context, "internship.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentQuery= "CREATE TABLE " + STUDENT_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRST_NAME + " VARCHAR(200) NOT NULL, " +
                LAST_NAME + " VARCHAR(200) NOT NULL, " +
                EMAIL + " VARCHAR(200) NOT NULL, " +
                NUMBER + " VARCHAR(13) NOT NULL, " +
                "specialty VARCHAR(300), " +
                "profile_picture BLOB, " +
                "school VARCHAR(200) NOT NULL, " +
                PASSWORD + " VARCHAR(200) NOT NULL);";

        String applicationQuery= "CREATE TABLE " + APPLICATION_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                APPLICATION_TABLE + "_date INTEGER NOT NULL DEFAULT (strftime('%s', 'now')), " +
                "status TEXT CHECK(status IN ('ACCEPTED','REJECTED','PENDING')) NOT NULL DEFAULT 'PENDING', " +
                "cv BLOB, " +
                APPLICATION_TABLE + "_letter BLOB," +
                STUDENT_TABLE + "_" + ID + " INTEGER NOT NULL, " +
                OFFER_TABLE + "_" + ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + STUDENT_TABLE + "_" + ID + ") REFERENCES " +STUDENT_TABLE+ "(" + ID + ") ON DELETE CASCADE," +
                "FOREIGN KEY(" + OFFER_TABLE + "_" + ID + ") REFERENCES " +OFFER_TABLE+ "(" + ID + ") ON DELETE CASCADE); ";

        String offerQuery= "CREATE TABLE " + OFFER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(200) NOT NULL, " +
                DESCRIPTION + " TEXT, " +
                "type TEXT CHECK(type IN ('INITIATION','OBSERVATION','PFA','PFE')) NOT NULL, " +
                "domaine VARCHAR(200) NOT NULL, " +
                "duration VARCHAR(200) NOT NULL, " +
                "start_date INTEGER NOT NULL, " +
                "end_date INTEGER NOT NULL, " +
                "post_date INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))," +
                COMPANY_TABLE + "_" + ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COMPANY_TABLE + "_" + ID + ") REFERENCES " +COMPANY_TABLE+ "(" + ID + ") ON DELETE CASCADE);";

        String companyQuery= "CREATE TABLE " + COMPANY_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " VARCHAR(200) NOT NULL, " +
                ADDRESSE + " VARCHAR(200), " +
                EMAIL + " VARCHAR(200) NOT NULL, " +
                NUMBER + " VARCHAR(200) NOT NULL, " +
                "fax VARCHAR(200), " +
                DESCRIPTION + " TEXT NOT NULL, " +
                "logo BLOB, " +
                PASSWORD + " VARCHAR(200) NOT NULL);";

        db.execSQL(studentQuery);
        db.execSQL(applicationQuery);
        db.execSQL(offerQuery);
        db.execSQL(companyQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + APPLICATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OFFER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COMPANY_TABLE);
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.execSQL("PRAGMA foreign_keys = ON;"); // Enable foreign keys
    }

    //methods pour ajouter et recuperer les donnees
    public boolean addStudent(StudentModel student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(FIRST_NAME,student.getFirstName());
        cv.put(LAST_NAME,student.getLastName());
        cv.put(EMAIL,student.getEmail());
        cv.put(NUMBER,student.getPhoneNumber());
        cv.put("specialty",student.getSpecialty());
        cv.put("profile_picture",student.getProfilePicture());
        cv.put("school",student.getSchool());
        cv.put(PASSWORD,student.getPassword());
        long result=-1;
        try{
            result=db.insert(STUDENT_TABLE,null,cv);
        }catch (Exception e){e.printStackTrace();}
        return result!=-1;
    }

    public List<StudentModel> getAllStudents(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<StudentModel> results=new ArrayList<>();
        StudentModel student;
        String query="SELECT * FROM "+STUDENT_TABLE+";";
        Cursor c=db.rawQuery(query,null);
        while(c.moveToNext()){
            student=new StudentModel(c.getLong(c.getColumnIndexOrThrow(ID)),
                    c.getString(c.getColumnIndexOrThrow(FIRST_NAME)),
                    c.getString(c.getColumnIndexOrThrow(LAST_NAME)),
                    c.getString(c.getColumnIndexOrThrow(EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(NUMBER)),
                    c.isNull(c.getColumnIndexOrThrow("specialty")) ? null : c.getString(c.getColumnIndexOrThrow("specialty")),
                    c.isNull(c.getColumnIndexOrThrow("profile_picture")) ? null : c.getBlob(c.getColumnIndexOrThrow("profile_picture")),
                    c.isNull(c.getColumnIndexOrThrow("school")) ? null : c.getString(c.getColumnIndexOrThrow("school")),
                    c.getString(c.getColumnIndexOrThrow(PASSWORD)));
            results.add(student);
        }
        c.close();
        return results;
    }

    public boolean addCompany(CompanyModel company){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,company.getName());
        cv.put(ADDRESSE,company.getAddresse());
        cv.put(EMAIL,company.getContactEmail());
        cv.put(NUMBER,company.getContactNumber());
        cv.put("fax",company.getFax());
        cv.put(DESCRIPTION,company.getDescription());
        cv.put("logo",company.getLogo());
        cv.put(PASSWORD,company.getPassword());
        long result = -1;
        try {
            result = db.insert(COMPANY_TABLE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public List<CompanyModel> getAllCompanies(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+COMPANY_TABLE+";";
        CompanyModel company;
        List<CompanyModel> results=new ArrayList<>();
        Cursor c=db.rawQuery(query,null);
        while(c.moveToNext()){
            long id = c.getLong(c.getColumnIndexOrThrow(ID));
            String name = c.getString(c.getColumnIndexOrThrow(NAME));
            String addresse = c.isNull(c.getColumnIndexOrThrow(ADDRESSE)) ? null : c.getString(c.getColumnIndexOrThrow(ADDRESSE));
            String email = c.getString(c.getColumnIndexOrThrow(EMAIL));
            String phoneNumber = c.getString(c.getColumnIndexOrThrow(NUMBER));
            String fax = c.isNull(c.getColumnIndexOrThrow("fax")) ? null : c.getString(c.getColumnIndexOrThrow("fax"));
            String description = c.isNull(c.getColumnIndexOrThrow(DESCRIPTION)) ? null : c.getString(c.getColumnIndexOrThrow(DESCRIPTION));
            byte[] logo = c.isNull(c.getColumnIndexOrThrow("logo")) ? null : c.getBlob(c.getColumnIndexOrThrow("logo"));
            String password = c.getString(c.getColumnIndexOrThrow(PASSWORD));

            company = new CompanyModel(id, name, addresse, email, phoneNumber, fax, description, logo, password);
            results.add(company);
        }
        c.close();
        return results;
    }

    public boolean addOffer(OfferModel offer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", offer.getTitle());
        cv.put(DESCRIPTION, offer.getDescription());
        cv.put("type", offer.getType().name());
        cv.put("domaine", offer.getDomaine());
        cv.put("duration", offer.getDuration());
        cv.put("start_date", offer.getStartDate().getTime());
        cv.put("end_date", offer.getEndDate().getTime());
        cv.put("post_date", offer.getPostDate().getTime());
        cv.put(COMPANY_TABLE + "_" + ID, offer.getCompanyId());

        long result = -1;
        try {
            result = db.insert(OFFER_TABLE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public List<OfferModel> getAllOffers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<OfferModel> results = new ArrayList<>();
        Cursor c = null;

        try {
            String query = "SELECT * FROM " + OFFER_TABLE + ";";
            c = db.rawQuery(query, null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(ID));
                String title = c.getString(c.getColumnIndexOrThrow("title"));
                String description = c.isNull(c.getColumnIndexOrThrow(DESCRIPTION)) ? null : c.getString(c.getColumnIndexOrThrow(DESCRIPTION));
                Type type = Type.valueOf(c.getString(c.getColumnIndexOrThrow("type")));
                String domaine = c.getString(c.getColumnIndexOrThrow("domaine"));
                String duration = c.getString(c.getColumnIndexOrThrow("duration"));
                Date startDate = new Date(c.getLong(c.getColumnIndexOrThrow("start_date")));
                Date endDate = new Date(c.getLong(c.getColumnIndexOrThrow("end_date")));
                Date postDate = new Date(c.getLong(c.getColumnIndexOrThrow("post_date")));
                long companyId = c.getLong(c.getColumnIndexOrThrow(COMPANY_TABLE + "_" + ID));

                OfferModel offer = new OfferModel(id, title, description, type, domaine, duration, startDate, endDate, postDate, companyId);
                results.add(offer);
            }
        } finally {
            if (c != null) c.close();
            db.close();
        }

        return results;
    }

    public boolean addApplication(ApplicationModel application) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(APPLICATION_TABLE + "_date", application.getApplicationDate().getTime()); // Convert Date to Unix timestamp
        cv.put("status", application.getStatus().name());
        cv.put("cv", application.getCv());
        cv.put(APPLICATION_TABLE + "_letter", application.getApplicationLetter());
        cv.put(STUDENT_TABLE + "_" + ID, application.getStudentId());
        cv.put(OFFER_TABLE + "_" + ID, application.getOfferId());

        long result = -1;
        try {
            result = db.insert(APPLICATION_TABLE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public List<ApplicationModel> getAllApplications() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ApplicationModel> results = new ArrayList<>();
        Cursor c = null;

        try {
            String query = "SELECT * FROM " + APPLICATION_TABLE + ";";
            c = db.rawQuery(query, null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(ID));
                Date applicationDate = new Date(c.getLong(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_date")));
                Status status = Status.valueOf(c.getString(c.getColumnIndexOrThrow("status")));
                byte[] cv = c.isNull(c.getColumnIndexOrThrow("cv")) ? null : c.getBlob(c.getColumnIndexOrThrow("cv"));
                byte[] applicationLetter = c.isNull(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter")) ? null : c.getBlob(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter"));
                long studentId = c.getInt(c.getColumnIndexOrThrow(STUDENT_TABLE + "_" + ID));
                long offerId = c.getInt(c.getColumnIndexOrThrow(OFFER_TABLE + "_" + ID));

                ApplicationModel application = new ApplicationModel(id, applicationDate, status, cv, applicationLetter, studentId, offerId);
                results.add(application);
            }
        } finally {
            if (c != null) c.close();
            db.close();
        }

        return results;
    }

    //methods to get data by id(?)(idk bit gha nvalidi)
    public StudentModel getStudentsById(long wantedId){
        SQLiteDatabase db=this.getReadableDatabase();
        StudentModel student=null;
        String query="SELECT * FROM "+STUDENT_TABLE+" WHERE "+ID+" = ?;";
        Cursor c=db.rawQuery(query,new String[]{String.valueOf(wantedId)});
        while(c.moveToNext()){
            student=new StudentModel(c.getLong(c.getColumnIndexOrThrow(ID)),c.getString(c.getColumnIndexOrThrow(FIRST_NAME)),c.getString(c.getColumnIndexOrThrow(LAST_NAME)),c.getString(c.getColumnIndexOrThrow(EMAIL)),c.getString(c.getColumnIndexOrThrow(NUMBER)),c.isNull(c.getColumnIndexOrThrow("specialty")) ? null : c.getString(c.getColumnIndexOrThrow("specialty")),c.isNull(c.getColumnIndexOrThrow("profile_picture")) ? null : c.getBlob(c.getColumnIndexOrThrow("profile_picture")),c.isNull(c.getColumnIndexOrThrow("school")) ? null : c.getString(c.getColumnIndexOrThrow("school")),c.getString(c.getColumnIndexOrThrow(PASSWORD)));
        }
        c.close();
        return student;
    }

    public CompanyModel getCompaniesById(long wantedId){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+COMPANY_TABLE+" WHERE "+ID+" = ?;";
        CompanyModel company=null;
        Cursor c=db.rawQuery(query,new String[]{String.valueOf(wantedId)});
        if(c.moveToFirst()){
            int id = c.getInt(c.getColumnIndexOrThrow(ID));
            String name = c.getString(c.getColumnIndexOrThrow(NAME));
            String addresse = c.isNull(c.getColumnIndexOrThrow(ADDRESSE)) ? null : c.getString(c.getColumnIndexOrThrow(ADDRESSE));
            String email = c.getString(c.getColumnIndexOrThrow(EMAIL));
            String phoneNumber = c.getString(c.getColumnIndexOrThrow(NUMBER));
            String fax = c.isNull(c.getColumnIndexOrThrow("fax")) ? null : c.getString(c.getColumnIndexOrThrow("fax"));
            String description = c.isNull(c.getColumnIndexOrThrow(DESCRIPTION)) ? null : c.getString(c.getColumnIndexOrThrow(DESCRIPTION));
            byte[] logo = c.isNull(c.getColumnIndexOrThrow("logo")) ? null : c.getBlob(c.getColumnIndexOrThrow("logo"));
            String password = c.getString(c.getColumnIndexOrThrow(PASSWORD));

            company = new CompanyModel(id, name, addresse, email, phoneNumber, fax, description, logo, password);
        }
        c.close();
        return company;
    }

    public OfferModel getOffersById(long wantedId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<OfferModel> results = new ArrayList<>();
        Cursor c;
        String query = "SELECT o.*, c.* FROM " + OFFER_TABLE + " o JOIN "+COMPANY_TABLE+" c ON o."+COMPANY_TABLE+"_"+ID+" = c."+ID+" WHERE o."+ID+" = ?;";
        c = db.rawQuery(query, new String[]{String.valueOf(wantedId)});
        OfferModel offer=null;

        if (c.moveToFirst()) {
            int id = c.getInt(c.getColumnIndexOrThrow(ID));
            String title = c.getString(c.getColumnIndexOrThrow("title"));
            String description = c.isNull(c.getColumnIndexOrThrow(DESCRIPTION)) ? null : c.getString(c.getColumnIndexOrThrow(DESCRIPTION));
            Type type = Type.valueOf(c.getString(c.getColumnIndexOrThrow("type")));
            String domaine = c.getString(c.getColumnIndexOrThrow("domaine"));
            String duration = c.getString(c.getColumnIndexOrThrow("duration"));
            Date startDate = new Date(c.getLong(c.getColumnIndexOrThrow("start_date")));
            Date endDate = new Date(c.getLong(c.getColumnIndexOrThrow("end_date")));
            Date postDate = new Date(c.getLong(c.getColumnIndexOrThrow("post_date")));
            long companyId = c.getLong((c.getColumnIndexOrThrow("c."+ID)));
            offer = new OfferModel(id, title, description, type, domaine, duration, startDate, endDate, postDate, companyId);
            results.add(offer);
        }
        c.close();


        return offer;
    }



    public ApplicationModel getApplicationsById(long wantedId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ApplicationModel> results = new ArrayList<>();
        String query = "SELECT a.*, s.*, o.* " +
                "FROM " + APPLICATION_TABLE + " a " +
                "JOIN " + STUDENT_TABLE + " s ON a." + STUDENT_TABLE + "_" + ID + " = s." + ID + " " +
                "JOIN " + OFFER_TABLE + " o ON a." + OFFER_TABLE + "_" + ID + " = o." + ID + " " +
                "WHERE a." + ID + " = ?;";
        Cursor c = db.rawQuery(query, new String[]{String.valueOf(wantedId)});
        ApplicationModel app=null;

        if (c.moveToFirst()) {
            // Map the application fields
            int id = c.getInt(c.getColumnIndexOrThrow("a." + ID));
            Date applicationDate = new Date(c.getLong(c.getColumnIndexOrThrow("a." + APPLICATION_TABLE + "_date")));
            Status status = Status.valueOf(c.getString(c.getColumnIndexOrThrow("a.status")));
            byte[] cv = c.isNull(c.getColumnIndexOrThrow("a.cv")) ? null : c.getBlob(c.getColumnIndexOrThrow("a.cv"));
            byte[] applicationLetter = c.isNull(c.getColumnIndexOrThrow("a." + APPLICATION_TABLE + "_letter")) ? null : c.getBlob(c.getColumnIndexOrThrow("a." + APPLICATION_TABLE + "_letter"));

            // Map the student fields
            long studentId = c.getLong(c.getColumnIndexOrThrow("s." + ID));

            // Map the offer fields
            long offerId = c.getLong(c.getColumnIndexOrThrow("o." + ID));

            app = new ApplicationModel(id, applicationDate, status, cv, applicationLetter, studentId, offerId);
        }
        c.close();
        return app;
    }

    public List<OfferModel> getCompnysOffers(long compId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<OfferModel> results = new ArrayList<>();
        Cursor c;
        String query = "SELECT * FROM " + OFFER_TABLE + " WHERE "+COMPANY_TABLE+"_"+ID+" = ?;";
        c = db.rawQuery(query, new String[]{String.valueOf(compId)});

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(ID));
            String title = c.getString(c.getColumnIndexOrThrow("title"));
            String description = c.isNull(c.getColumnIndexOrThrow(DESCRIPTION)) ? null : c.getString(c.getColumnIndexOrThrow(DESCRIPTION));
            Type type = Type.valueOf(c.getString(c.getColumnIndexOrThrow("type")));
            String domaine = c.getString(c.getColumnIndexOrThrow("domaine"));
            String duration = c.getString(c.getColumnIndexOrThrow("duration"));
            Date startDate = new Date(c.getLong(c.getColumnIndexOrThrow("start_date")));
            Date endDate = new Date(c.getLong(c.getColumnIndexOrThrow("end_date")));
            Date postDate = new Date(c.getLong(c.getColumnIndexOrThrow("post_date")));
            long companyId = c.getInt(c.getColumnIndexOrThrow(COMPANY_TABLE + "_" + ID));

            OfferModel offer = new OfferModel(id, title, description, type, domaine, duration, startDate, endDate, postDate, companyId);
            results.add(offer);
        }
        c.close();


        return results;
    }

    public List<ApplicationModel> getStudentsApplications(int studId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ApplicationModel> results = new ArrayList<>();
        Cursor c;
        String query = "SELECT * FROM " + APPLICATION_TABLE + " WHERE "+STUDENT_TABLE+"_"+ID+" = ?;";
        c = db.rawQuery(query, new String[]{String.valueOf(studId)});

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(ID));
            Date applicationDate = new Date(c.getLong(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_date")));
            Status status = Status.valueOf(c.getString(c.getColumnIndexOrThrow("status"))); // String to Enum
            byte[] cv = c.isNull(c.getColumnIndexOrThrow("cv")) ? null : c.getBlob(c.getColumnIndexOrThrow("cv"));
            byte[] applicationLetter = c.isNull(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter")) ? null : c.getBlob(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter"));
            long studentId = c.getInt(c.getColumnIndexOrThrow(STUDENT_TABLE + "_" + ID));
            long offerId = c.getInt(c.getColumnIndexOrThrow(OFFER_TABLE + "_" + ID));

            ApplicationModel application = new ApplicationModel(id, applicationDate, status, cv, applicationLetter, studentId, offerId);
            results.add(application);
        }
        c.close();

        return results;
    }

    public List<ApplicationModel> getOffersApplications(long offId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ApplicationModel> results = new ArrayList<>();
        Cursor c;
        String query = "SELECT * FROM " + APPLICATION_TABLE + " WHERE "+OFFER_TABLE+"_"+ID+" = ?;";
        c = db.rawQuery(query, new String[]{String.valueOf(offId)});

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(ID));
            Date applicationDate = new Date(c.getLong(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_date")));
            Status status = Status.valueOf(c.getString(c.getColumnIndexOrThrow("status"))); // String to Enum
            byte[] cv = c.isNull(c.getColumnIndexOrThrow("cv")) ? null : c.getBlob(c.getColumnIndexOrThrow("cv"));
            byte[] applicationLetter = c.isNull(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter")) ? null : c.getBlob(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter"));
            long studentId = c.getInt(c.getColumnIndexOrThrow(STUDENT_TABLE + "_" + ID));
            long offerId = c.getInt(c.getColumnIndexOrThrow(OFFER_TABLE + "_" + ID));

            ApplicationModel application = new ApplicationModel(id, applicationDate, status, cv, applicationLetter, studentId, offerId);
            results.add(application);
        }
        c.close();

        return results;
    }

    //Fcts pour suprimer les donnees a partir de l'ID
    public boolean deleteCompanyById(int companyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(COMPANY_TABLE, ID + " = ?", new String[]{String.valueOf(companyId)});
        db.close();
        return result > 0;
    }

    public boolean deleteStudentById(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(STUDENT_TABLE, ID + " = ?", new String[]{String.valueOf(studentId)});
        db.close();
        return result > 0; // Returns true if at least one row was deleted
    }

    public boolean deleteOfferById(int offerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(OFFER_TABLE, ID + " = ?", new String[]{String.valueOf(offerId)});
        db.close();
        return result > 0;
    }

    public boolean deleteApplicationById(int applicationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(APPLICATION_TABLE, ID + " = ?", new String[]{String.valueOf(applicationId)});
        db.close();
        return result > 0;
    }

    //Method pour les utilisateurs(entrprises et stagoaires)

    //For interns
    public boolean applyToOffer(int studentId, int offerId, byte[] cv, byte[] applicationLetter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvValues = new ContentValues();
        cvValues.put(STUDENT_TABLE + "_" + ID, studentId);
        cvValues.put(OFFER_TABLE + "_" + ID, offerId);
        cvValues.put("cv", cv);
        cvValues.put(APPLICATION_TABLE + "_letter", applicationLetter);
        long result = -1;
        try {
            result = db.insert(APPLICATION_TABLE, null, cvValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    //For Companies(bach ypostiw offre jdid)
    public boolean postOffer(String title, String description, Type type, String domaine, String duration, Date startDate, Date endDate, int companyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put(DESCRIPTION, description);
        cv.put("type", type.name());
        cv.put("domaine", domaine);
        cv.put("duration", duration);
        cv.put("start_date", startDate.getTime());
        cv.put("end_date", endDate.getTime());
        cv.put(COMPANY_TABLE + "_" + ID, companyId);
        long result = -1;
        try {
            result = db.insert(OFFER_TABLE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    //For Companies(bach ychofo ga3 les demandes de stage)
    public List<ApplicationModel> consultOfferApplications(int wantedOfferId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ApplicationModel> results = new ArrayList<>();
        Cursor c;
        String query = "SELECT * FROM " + APPLICATION_TABLE + " WHERE "+OFFER_TABLE+"_"+ID+" = ?;";
        c = db.rawQuery(query, new String[]{String.valueOf(wantedOfferId)});

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(ID));
            Date applicationDate = new Date(c.getLong(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_date")));
            Status status = Status.valueOf(c.getString(c.getColumnIndexOrThrow("status"))); // String to Enum
            byte[] cv = c.isNull(c.getColumnIndexOrThrow("cv")) ? null : c.getBlob(c.getColumnIndexOrThrow("cv"));
            byte[] applicationLetter = c.isNull(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter")) ? null : c.getBlob(c.getColumnIndexOrThrow(APPLICATION_TABLE + "_letter"));
            long studentId = c.getInt(c.getColumnIndexOrThrow(STUDENT_TABLE + "_" + ID));
            long offerId = c.getInt(c.getColumnIndexOrThrow(OFFER_TABLE + "_" + ID));

            ApplicationModel application = new ApplicationModel(id, applicationDate, status, cv, applicationLetter, studentId, offerId);
            results.add(application);
        }
        c.close();

        return results;
    }

    //For Companies(bach ychofo chkoun sift demande lihom)
    public List<StudentModel> consultOfferCandidates(int wantedOfferId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<StudentModel> results = new ArrayList<>();
        Cursor c;
        String query = "SELECT student.*\n" +
                "FROM student\n" +
                "INNER JOIN application\n" +
                "ON student.id = application.student_id\n" +
                "WHERE application.offer_id = ?;";
        c = db.rawQuery(query, new String[]{String.valueOf(wantedOfferId)});

        while (c.moveToNext()) {
            StudentModel student = new StudentModel(c.getLong(c.getColumnIndexOrThrow(ID)),
                    c.getString(c.getColumnIndexOrThrow(FIRST_NAME)),
                    c.getString(c.getColumnIndexOrThrow(LAST_NAME)),
                    c.getString(c.getColumnIndexOrThrow(EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(NUMBER)),
                    c.isNull(c.getColumnIndexOrThrow("specialty")) ? null : c.getString(c.getColumnIndexOrThrow("specialty")),
                    c.isNull(c.getColumnIndexOrThrow("profile_picture")) ? null : c.getBlob(c.getColumnIndexOrThrow("profile_picture")),
                    c.isNull(c.getColumnIndexOrThrow("school")) ? null : c.getString(c.getColumnIndexOrThrow("school")),
                    c.getString(c.getColumnIndexOrThrow(PASSWORD)));
            results.add(student);
        }
        c.close();

        return results;
    }

    //Update methods

    public boolean updateStudent(int id, String name, String email, String phoneNumber, String fieldOfStudy, String school, String password, byte[] profilePicture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, name);
        values.put(EMAIL, email);
        values.put(NUMBER, phoneNumber);
        values.put("specialty", fieldOfStudy);
        values.put("school", school);
        values.put(PASSWORD, password);
        values.put("profile_picture", profilePicture);
        int result = db.update(STUDENT_TABLE, values, "id = ?", new String[]{String.valueOf(id)});
        return result>0;
    }

    //Update Company
    public boolean updateCompany(int id, String name, String address, String email, String phone, String fax, String description, String password, byte[] logo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(ADDRESSE, address);
        values.put(EMAIL, email);
        values.put(NUMBER, phone);
        values.put(PASSWORD, password);
        values.put("logo", logo);
        values.put("fax", fax);
        values.put(DESCRIPTION, description);
        //id est utilisé dans cette clause pour verifier la ligne à mettre à jour
        int result = db.update(COMPANY_TABLE, values, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    //Update Offer
    public boolean updateOffer(int id, String title, String description, String field, String duration, Date postedDate, Date startDate, Date endDate, Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put(DESCRIPTION, description);
        values.put("domaine", field);
        values.put("duration", duration);
        values.put("post_date", postedDate.getTime());
        values.put("start_date", startDate.getTime());
        values.put("end_date", endDate.getTime());
        values.put("type", type.name());
        int result = db.update(OFFER_TABLE, values, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    //Update application
    public boolean updateApplication(int id, Date applicationDate, Status status, byte[] cv, byte[] applicationLetter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("application_date", applicationDate.getTime());
        values.put("status", status.name());
        values.put("cv", cv);
        values.put("application_letter", applicationLetter);
        int result = db.update(APPLICATION_TABLE, values, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public CompanyModel getCompanyByEmailndPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COMPANY_TABLE + " WHERE " + EMAIL + " = ? AND " + PASSWORD + " = ?;";
        Cursor c = db.rawQuery(query, new String[]{email, password});
        CompanyModel company=null;
        try {
            if (c.moveToFirst()) {
                int id = c.getInt(c.getColumnIndexOrThrow(ID));
                String name = c.getString(c.getColumnIndexOrThrow(NAME));
                String addresse = c.isNull(c.getColumnIndexOrThrow(ADDRESSE)) ? null : c.getString(c.getColumnIndexOrThrow(ADDRESSE));
                String newEmail = c.getString(c.getColumnIndexOrThrow(EMAIL));
                String phoneNumber = c.getString(c.getColumnIndexOrThrow(NUMBER));
                String fax = c.isNull(c.getColumnIndexOrThrow("fax")) ? null : c.getString(c.getColumnIndexOrThrow("fax"));
                String description = c.isNull(c.getColumnIndexOrThrow(DESCRIPTION)) ? null : c.getString(c.getColumnIndexOrThrow(DESCRIPTION));
                byte[] logo = c.isNull(c.getColumnIndexOrThrow("logo")) ? null : c.getBlob(c.getColumnIndexOrThrow("logo"));
                String newPassword = c.getString(c.getColumnIndexOrThrow(PASSWORD));

                company = new CompanyModel(id, name, addresse, newEmail, phoneNumber, fax, description, logo, newPassword);
            }
        } finally {
            c.close(); // Always close the Cursor
        }

        return company;
    }


    public StudentModel getStudentsByEmailndPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + EMAIL + " = ? AND " + PASSWORD + " = ?;";
        Cursor c = db.rawQuery(query, new String[]{email, password});
        StudentModel student=null;
        try {
            if (c.moveToFirst()) {
                student = new StudentModel(
                        c.getLong(c.getColumnIndexOrThrow(ID)),
                        c.getString(c.getColumnIndexOrThrow(FIRST_NAME)),
                        c.getString(c.getColumnIndexOrThrow(LAST_NAME)),
                        c.getString(c.getColumnIndexOrThrow(EMAIL)),
                        c.getString(c.getColumnIndexOrThrow(NUMBER)),
                        c.isNull(c.getColumnIndexOrThrow("specialty")) ? null : c.getString(c.getColumnIndexOrThrow("specialty")),
                        c.isNull(c.getColumnIndexOrThrow("profile_picture")) ? null : c.getBlob(c.getColumnIndexOrThrow("profile_picture")),
                        c.isNull(c.getColumnIndexOrThrow("school")) ? null : c.getString(c.getColumnIndexOrThrow("school")),
                        c.getString(c.getColumnIndexOrThrow(PASSWORD))
                );
            }
        } finally {
            c.close(); // Always close the Cursor
        }

        return student;
    }
    public StudentModel getApplicationSender(ApplicationModel applicationModel){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + ID + " = ?;";
        Cursor c = db.rawQuery(query, new String[]{String.valueOf(applicationModel.getStudentId())});
        StudentModel student=null;
        try {
            if (c.moveToFirst()) {
                student = new StudentModel(
                        c.getLong(c.getColumnIndexOrThrow(ID)),
                        c.getString(c.getColumnIndexOrThrow(FIRST_NAME)),
                        c.getString(c.getColumnIndexOrThrow(LAST_NAME)),
                        c.getString(c.getColumnIndexOrThrow(EMAIL)),
                        c.getString(c.getColumnIndexOrThrow(NUMBER)),
                        c.isNull(c.getColumnIndexOrThrow("specialty")) ? null : c.getString(c.getColumnIndexOrThrow("specialty")),
                        c.isNull(c.getColumnIndexOrThrow("profile_picture")) ? null : c.getBlob(c.getColumnIndexOrThrow("profile_picture")),
                        c.isNull(c.getColumnIndexOrThrow("school")) ? null : c.getString(c.getColumnIndexOrThrow("school")),
                        c.getString(c.getColumnIndexOrThrow(PASSWORD))
                );
            }
        } finally {
            c.close(); // Always close the Cursor
        }

        return student;
    }


    //admin functions

    public long countStudents(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = 0;
        String query = "SELECT COUNT(*) FROM student;";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                count = cursor.getLong(0); // Retrieve the count from the first column
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Always close the cursor to avoid memory leaks
            }
        }
        return count;
    }

    public long countCompanies(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = 0;
        String query = "SELECT COUNT(*) FROM company;";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                count = cursor.getLong(0); // Retrieve the count from the first column
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Always close the cursor to avoid memory leaks
            }
        }
        return count;
    }

    public long countAll(){
        return countStudents()+countCompanies();
    }



    //3yiiiiit

}
