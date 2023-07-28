export const treeValues = [
    {
     
        label: 'User Conroller',
        icon: 'pi pi-users',
        children: [
            { label: 'New User Conroller', 
              icon: 'pi pi-fw pi-eye',
              data: 'waitingUser' 
            },
            { label: 'Existing User', 
            icon: 'pi pi-fw pi-id-card',
            data: 'existingUser' 
            },

            { label: 'Role Details', 
            icon: 'pi pi-fw pi-shield',
            data: 'roles' 
            },
            { label: 'User Role Controller', 
            icon: 'pi pi-fw pi-shield',
            data: 'userRole' 
            }
          
        ]
    },
    {
       
        label: 'Restaurant',
        data: 'Home Folder',
        icon: 'pi pi-fw pi-home',
        children: [
            { 
             label: 'Invoices.txt',
              icon: 'pi pi-fw pi-file',
               data: 'Invoices for this month' 
            }]
    },

    {
       
        label: 'Food Category',
        data: 'Home Folder',
        icon: 'pi pi-fw pi-reddit',
        children: [
            { 
             label: 'Invoices.txt',
              icon: 'pi pi-fw pi-file',
               data: 'Invoices for this month' 
            }]
    },


    {
       
        label: 'Foods',
        data: 'Home Folder',
        icon: 'pi pi-fw pi-gift',
        children: [
            { 
             label: 'Invoices.txt',
              icon: 'pi pi-fw pi-file',
               data: 'Invoices for this month' 
            }]
    },
    {
       
        label: 'Dish Controller',
        data: 'Home Folder',
        icon: 'pi pi-fw pi-stop-circle',
        children: [
            { 
             label: 'Invoices.txt',
              icon: 'pi pi-fw pi-file',
               data: 'Invoices for this month' 
            }]
    },

    {
       
        label: 'Orders',
        data: 'Home Folder',
        icon: 'pi pi-fw pi-money-bill',
        children: [
            { 
             label: 'Invoices.txt',
              icon: 'pi pi-fw pi-file',
               data: 'Invoices for this month' 
            }]
    }

    
]