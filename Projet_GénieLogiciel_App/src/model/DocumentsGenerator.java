package model ;


public class DocumentsGenerator{

public DocumentsGenerator(Demande demande){
    ConnexionBasedonne dbConnection = new ConnexionBasedonne();
    int studentId = demande.getIdEtudiant();
    String selectedDocumentType = demande.getType();

    ModifyPDF attestaionScolarite = new ModifyPDF();
    ModifyPDFC convention = new ModifyPDFC();
    Resultat releves = new Resultat();
    ModifyPDFR attestationReussite= new ModifyPDFR();
    switch (selectedDocumentType) {
        case "Relevés de Notes":
            releves.generatePDFFromDatabase(demande);
            break;
        case "certificat de scolarité":
            attestaionScolarite.modifyAttestationPDF(dbConnection, demande);
            break;
        case "Stage":
            convention.modifyInternshipPDF(dbConnection, demande); 
            break;
        case "Attestation de réussite" :
            attestationReussite.modifyAttestationPDF(dbConnection, demande);

        default:
            System.out.println("Unsupported document type "+demande.getTypeDemande());
            break;
}


}

// public static void main(String[] args) {
//     // You can add test logic or instantiate Demande objects for testing.
//     // demandes.add(new Demande(2,"2021-12-06","Relevés de Notes",1,"email","nom     2","prenom",0));
//     // Example: Creating a test Demande object
//     Demande testDemande = new Demande(1,"2021-12-06","Relevés de Notes",1,"email","nom     2","prenom",0);
//     Demande testDemande1 = new Demande(1,"2021-12-06","certificat de scolarité",1,"email","nom     2","prenom",0);
//     Demande testDemande2 = new Demande(1,"2021-12-06","Stage",1,"email","nom     2","prenom",0);
//     Demande testDemande3 = new Demande(1,"2021-12-06","Attestation de réussite",1,"email","nom     2","prenom",0);


//     // Example: Creating a DocumentsGenerator and generating the document
//     new DocumentsGenerator(testDemande);
//     new DocumentsGenerator(testDemande2);
//     new DocumentsGenerator(testDemande1);
//     new DocumentsGenerator(testDemande3);
    
//     // You can create more test cases or use a loop to test different scenarios.
// }
}
