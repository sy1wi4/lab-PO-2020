# Pytanie: jak zaimplementować mechanizm, który wyklucza pojawienie się dwóch zwierząt w tym samym miejscu?
Odp.: Możnaby wykorzystać HashMapę (w klasie World), w której przechowywać będziemy zajęte pola mapy, dzięki czemu sprawdzenie, czy pole nie jest zajęte odbywa się w O(1). Sprawdzamy zarówno przy tworzeniu zwierzęcia, jak i przy ruchu wprzód lub w tył - aktualizujemy zajęte pola.

