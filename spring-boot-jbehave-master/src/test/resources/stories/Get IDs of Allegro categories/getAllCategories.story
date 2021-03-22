Feature: Get IDs of Allegro categories endpoint

Scenario: System prezentuje listę głównych kategorii Allegro
Given Użytkownik pobiera token JWT
And Accept type jest ustawiony na application/vnd.allegro.public.v1+json
When Użytkownik odpytuje https://api.allegro.pl/sale/categories endpoint
Then System prezentuje listę głównych kategorii Allegro


Scenario: Użytkownik widzi tylko główne kategorie
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



