Feature: Get IDs of Allegro categories endpoint


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
