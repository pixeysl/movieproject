package com.thalesgroup.restlib;

/**
 * This class provides rest-api services from TMDb
 */
class TmdbHttpQuery implements IHttpQuery {

    /**
     * Send http request to find movie
     * @param caller
     * @param httpQueryParameter
     */
    @Override
    public void findMovie(IRestApiListener caller, HttpQueryParameter httpQueryParameter) {
        new TmdbRestApi(caller).execute(httpQueryParameter);
    }

    /**
     * The root name of the JSONObject result returned
     * @return root name
     */
    @Override
    public String getResultRoot() {
        return TmdbRestApi.TMDB_RESULT_ROOT;
    }
}
