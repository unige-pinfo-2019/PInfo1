
/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
    production: false,
    items_url: 'https://localhost/api/items',
    user_url: 'https://localhost/api/user',
    ad_url: 'https://localhost/api/ad',
    statistic_url: 'https://localhost/api/statistic',

    // user_url: 'http://localhost:12080',
    // items_url: 'http://localhost:10080',
    // ad_url: 'http://localhost:11080',
    // statistic_url: 'http://localhost:14080',




    apiUrl: 'URL_DE_API',
    keycloak: {
        url: 'https://localhost/auth',
        realm: 'apigw',
        clientId: 'web-sso',
        checkLoginIframe: true,
        onLoad: 'login-required',
        responseMode: 'fragment',
	}

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
