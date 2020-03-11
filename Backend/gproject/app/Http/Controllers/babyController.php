<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\baby;
use App\User;

class babyController extends Controller
{
    /*public function user(){
        $data = User::all();
        return $data;
     //return response()->json(userModel::get(),200);
     //echo "data from DB";
    }*/
    public function insert(Request $request){
        $user = User::where('api_token', $request->api_token)->first();
        if (!$user) return response()->json('Not found', 404);
        $baby = new baby;
        $baby->id=$request->input('id');
        //$request->input('id');
        $baby->user_id = $user->id;
        $baby->name=$request->input('name');
        $baby->gender=$request->input('gender');
        $baby->birthdate=$request->input('birthdate');
        $baby->weight=$request->input('weight');
        $baby->length=$request->input('length');
        $result = $baby->save();
        /*if($result ==1){
         return "Record is inserted";
        }*/
        if ($user) 
        return response()->json('Inserted Successfully', 200);
        
    }
}
