Feature: Get parameters supported by a category endpoint

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



