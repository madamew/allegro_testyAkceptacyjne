Projekt zawiera testy akceptacyjne dla endpointa: https://api.allegro.pl/sale/categories/

Do napisania testów został wykorzystany zestaw: JBehave + Spring

Testami zostały pokryte 3 metody:

1.Get a category by ID

      Scenario: System prezentuje szczegóły kategorii - Dom i Ogród
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories/5 endpoint
      Then System prezentuje szczegóły kategorii

      Scenario: Kategoria o podanym ID nie istnieje
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories/-1 endpoint
      Then Kategoria z id= -1 nie istnieje

2.Get IDs of Allegro categories

      Scenario: System prezentuje listę głównych kategorii Allegro
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories endpoint
      Then System prezentuje listę głównych kategorii Allegro

      Scenario: Użytkownik otrzymuje tylko główne kategorie
      Given Główne kategorie:
      |names|
      |Dom i Ogród|
      |Dziecko|
      |Elektronika|
      |Firma i usługi|
      |Kolekcje i sztuka|
      |Kultura i rozrywka|
      |Moda|
      |Motoryzacja|
      |Nieruchomości|
      |Sport i turystyka|
      |Supermarket|
      |Uroda|
      |Zdrowie|
      And Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories endpoint
      Then Widoczne są tylko nazwy z podanej listy głównych kategorii
      And Widocznych jest 13 głównych kategorii
      And Widoczne są tylko kategorie dla których parent = null
      And Widoczne są tylko kategorie dla których leaf = false
      
      
      Scenario: System pokazuje błąd
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories endpoint
      Then System prezentuje błąd
      
      Scenario: System prezentuje listę podkategorii dla zadanego parent.id
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories?parent.id=5 endpoint
      Then System prezentuje listę podkategorii
      And Widoczne są tylko kategorie dla których parent = 5
      
      Scenario: Nie istnieją podkategorie dla zadanego parent.id
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories?parent.id=-1 endpoint
      Then Kategoria z id= -1 nie istnieje

3.Get parameters supported by a category

      Scenario: System prezentuje listę parametrów dla zadanej kategorii
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories/5/parameters endpoint
      Then System prezentuje listę parametrów dla zadanej kategorii
      
      
      Scenario: Kategoria o podanym ID nie istnieje
      Given Użytkownik pobiera token JWT
      And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
      When Użytkownik odpytuje https://api.allegro.pl/sale/categories/-1/parameters endpoint
      Then Kategoria z id= -1 nie istnieje

Aby uruchomić testy należy w głównym katalogu uruchomić polecenie 
        
        mvn clean install

Efektem polecenia będzie:

            Reports view generated with 5 stories (of which 0 pending) containing 9 scenarios (of which 0 pending)
            [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.77 s - in runner.TestRunner

