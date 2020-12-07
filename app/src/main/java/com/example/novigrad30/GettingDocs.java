package com.example.novigrad30;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

////import android.support.v4.app.AppCompatActivity;

public class GettingDocs extends AppCompatActivity implements View.OnClickListener /*  implementing click listener */ {

    //a referejce to the storage database
    private StorageReference storageReference ;
    //a constant to track the file chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    //Buttons
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonSoumission;

    //ImageView
    private ImageView imageView;

    //a Uri object to store file path
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_docs);
         storageReference  = FirebaseStorage.getInstance().getReference();
        //getting views from layout
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonSoumission = (Button)findViewById(R.id.button_soumission);

        imageView = (ImageView) findViewById(R.id.imageView);

        //attaching listener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }
    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        //if the clicked button is choose
        if (view == buttonChoose) {
            showFileChooser();
        }
        //if the clicked button is upload
        else if (view == buttonUpload) {

            uploadFile();

        }

        buttonSoumission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Demande demande;
                //Ajouter le nom du fichier dans l'objet demande
                if(SelectionServicesClients.getChoice_form().equals("pc"))
                {
                    //recuperer la demande du formulaire pc
                    Client client = MainActivity.getCurrentClient();
                    demande = Pc_form.get_demande();
                    demande.setFormulaires_requis(Pc_form.getData());
                    demande.setFormulaires_fournis(client.getNom()+ " "+client.getPrenom() + "file.jpg");
                }
                else
                {
                    Client client = MainActivity.getCurrentClient();
                    demande = Form.get_demande();
                    demande.setFormulaires_fournis(client.getNom()+ " "+client.getPrenom() + "file.jpg");
                    demande.setFormulaires_requis(Form.getData());
                }
                //soumettre la  demande en ajoutant la demande dans la liste des demandes de la succursale
                DatabaseReference SuccursaleDB;

                SuccursaleDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/Succursale Toronto" + /*currentUser.getNomSuccursale() +*/ "/Demandes");
                SuccursaleDB.child(MainActivity.getCurrentClient().getNom() + " " + MainActivity.getCurrentClient().getPrenom()+"/"+SelectionServicesClients.getChosen_one().getServiceName()).setValue(demande);
                //indiquez à l'utilisateur
                Toast.makeText(GettingDocs.this, "Demande soumise! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
//this method will upload the file
public void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();


            Client client = MainActivity.getCurrentClient();
            StorageReference riversRef = storageReference.child("images/"+client.getNom()+ " "+client.getPrenom() + "file.jpg");
            //StorageReference riversRef = storageReference.child("images/pic.jpg");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }

}