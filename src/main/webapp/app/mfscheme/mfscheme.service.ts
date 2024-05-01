import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { environment } from "environments/environment";
import { MfSchemeDTO } from "./mfscheme.model";

@Injectable({
    providedIn: 'root',
  })
  export class MfSchemeService {

    http = inject(HttpClient);
    resourcePath = environment.apiPath + '/api/schemes';

    getAllMfSchemes() {
      return this.http.get<MfSchemeDTO[]>(this.resourcePath);
    }

    getMfSchemeInfo(schemeCode: number) {
      return this.http.get<MfSchemeDTO>(this.resourcePath + '/' + schemeCode);
    }
  }