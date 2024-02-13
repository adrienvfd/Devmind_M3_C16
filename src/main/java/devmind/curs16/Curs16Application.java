package devmind.curs16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Curs16Application {

	public static void main(String[] args) {
		SpringApplication.run(Curs16Application.class, args);
	}

}

//În lipsa unei baze de date,
// adaugă o colecție aplicației tale prin care să memorezi utilizatorii înregistrați.
// De fiecare dată când endpoint-ul /register este apelat cu succes, un nou User ar trebui să fie adăugat la această colecție.

//Modifică metoda handleRegister astfel încât endpoint-ul să întoarcă status code 400, împreună cu un mesaj sugestiv de eroare, în cazul în care există deja un utilizator cu același email.
//Adaugă un nou endpoint la adresa /login. Acesta va primi un request body de forma:
//{
//    "email": "test@test.com",
//    "password": "mySpecialPassword!"
//}
//și va verifica dacă există un utilizator cu aceeași adresă de email și aceeași parolă.
//
//Dacă autentificarea se face cu succes, endpoint-ul va trebui să returneze status code 200 împreună cu următoarele informații despre utilizator:
// firstName, lastName, email în format JSON.
//Dacă nu este găsit un utilizator cu același email și aceeași parolă, endpoint-ul va trebui să întoarcă un răspuns cu status code 400 și un mesaj sugestiv.
//Adaugă reguli de validare a parametrilor pentru noul endpoint, similare cu cele adăugate pentru /register.
//Adaugă o nouă colecție aplicației tale prin care să memorezi utilizatorii autentificați.
// De fiecare dată când endpoint-ul /login este apelat cu succes, un User ar trebui să fie adăugat la această colecție.
//Modifică endpoint-ul /login astfel încât să returneze status code 202 în cazul în care utilizatorul este deja autentificat,
// împreună cu detaliile precizate anterior. Hint: ResponseEntity.
//[Extra] Adaugă un endpoint la adresa /logout/{email} unde email este un parametru de tip PathVariable.
// Acest endpoint va trebui să verifice dacă utilizatorul cu emailul primit ca parametru este autentificat și, în caz afirmativ,
// să îl șteargă din lista utilizatorilor autentificați.