### List API

- Public: /api/public
    - [x] GET: /foods
    - [x] GET: /menus


- Auth: /api/auth
    - [x] POST: /login
    - [ ] POST: /logout


- Admin: /api/admin
    - [x] GET: /staffs
    - [x] GET: /staffs/{id}
    - [x] POST: /staffs/create
    - [x] DELETE: /staffs/{username}
    - [x] PUT: /staffs/{username}/role


- Manager: /api/manager
    - [x] GET: /foods/findallfoodbyname
    - [x] POST: /foods/create
    - [x] POST: /foods/addfoodtomenu
    - [x] POST: /foods/findallfoodfrommenu
    - [x] DELETE: /foods/removefoodfrommenu

    - [x] GET: /menus/findallmenubyname
    - [x] POST: /menus/create
    - [ ] PUT: /menus/{id}
    - [ ] DELETE: /menus/{id}

    - [ ] GET: /orders
    - [ ] GET: /orders/{id}
    - [ ] POST: /orders/create
    - [ ] PUT: /orders/{id}
    - [ ] DELETE: /orders/{id}

    - [ ] GET: /tables
    - [ ] GET: /tables/{id}
    - [ ] POST: /tables/create
    - [ ] PUT: /tables/{id}
    - [ ] DELETE: /tables/{id}

    - [ ] GET: /customers
    - [ ] GET: /customers/{id}
    - [ ] POST: /customers/create
    - [ ] PUT: /customers/{id}
    - [ ] DELETE: /customers/{id}

    - [ ] GET: /bills
    - [ ] GET: /bills/{id}
    - [ ] POST: /bills/create
    - [ ] PUT: /bills/{id}
    - [ ] DELETE: /bills/{id}
    - [ ] GET: /bill-details
    - [ ] GET: /bill-details/{id}
    - [ ] POST: /bill-details/create
    - [ ] PUT: /bill-details/{id}
    - [ ] DELETE: /bill-details/{id}
    - [ ] GET: /bill-details/{id}/foods
    - [ ] GET: /bill

