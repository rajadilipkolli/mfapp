export class MfSchemeDTO {

    constructor(data:Partial<MfSchemeDTO>) {
        Object.assign(this, data);
    }

    schemeCode?: number|null;
    amc?: string|null;
    payout?: string|null;
    schemeName?: string|null;
    schemeType?: string|null;
    nav?: number;
    date?: string;
}