Feature: Get a category by ID endpoint

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



