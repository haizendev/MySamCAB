# MySamCAB Technical Test

Technical test for my recruitment in the transport company MySamCAB. This application was coded in an hour following the request.


## Specifications


<a href="https://mysam.fr"><img src="https://mysam.fr/wp-content/uploads/2018/09/LOGO_MYSAM_FLAT-copie.png" title="MySam" alt="MySam"></a>


L’objectif de cet exercice est de développer une version très “light” de l’application MySam.

Dans notre application, des clients peuvent commander des courses auprès de chauffeurs.
Une course peut avoir plusieurs états :
- en attente de chauffeur
- affectée à un chauffeur
- en cours
- terminée

Une fois la course terminée, trois transactions financières ont lieu : Une transaction de débit
client, une transaction de crédit chauffeur (85% du montant de la transaction client), et une
transaction de crédit MySam (les 15% restants).

Ecrire une petite application serveur (dans le langage/framework de votre choix : Java &
Hibernate, PHP & Symfony, ...), qui disposera de 3 points d’accès REST :
- /api/trip/accept?tripId=XXX
- /api/trip/start?tripId=XXX
- /api/trip/end?tripId=XXX

Vous développerez également (de manière la plus simple possible) les objets modèle
associés, ainsi que les services de persistence qui vous semblent pertinents.

Attention : il s’agit d’un test RAPIDE. Il ne doit pas compiler et intégrer d’architecture
complexe. Nous souhaitons simplement évaluer vos capacités de conception et de
développement.
