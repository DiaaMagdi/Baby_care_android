<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Str;
use App\User;

class userController extends Controller
{
    /*public function user(){
        $data = User::all();
        return $data;
     //return response()->json(userModel::get(),200);
     //echo "data from DB";
    }*/
    public function insert(Request $request){
        //print_r($request->input());
        /*$user = new User;
        $user->id=$request->input('id');
        $user->name=$request->input('name');
        $user->email=$request->input('email');
        $user->gender=$request->input('gender');
        $user->type=$request->input('type');
        //$user->role=$request->input('role');
        $user->password=Hash::make($request->input('password'));
        $result = $user->save();
        if($result ==1){
            return "Record is inserted";
        }*/
///////////////////Registration//////////////////////////
        $validator = Validator::make($request->all(),[
            'name'=>'required',
            'email'=>'required|max:191|unique:users',
            'gender'=>'required',
            'type'=>'required',
            'password'=>'required'

        ]);
        if($validator->fails()){
            return $validator->errors();
        }else{
        $user = new User;
        $user->id=$request->input('id');
        $user->name=$request->input('name');
        $user->email=$request->input('email');
        $user->gender=$request->input('gender');
        $user->type=$request->input('type');
        //$user->role=$request->input('role');
        $user->password=Hash::make($request->input('password'));
        $user->api_token=Str::random(60);
        $result = $user->save();
        if($result ==1){
            return "Record is inserted";
            }
            
        }
    }
//////////////////////login/////////////////////////////
    public function index(Request $request){
        if(auth()->attempt(['email'=>$request->input('email'),
        'password'=>$request->input('password')])){
            $user=auth()->user();
            $user->api_token =Str::random(60);
            $user->save();
            return $user;
        }
            return "NO";    
    }
//////////////////////logout////////////////////////////
    public function logout(){
        if(auth()->user()){
            $user = auth()->user();
            $user->api_token = null;
            $user->save();
            return response()->json(['message'=>'Thank you for using our application']);
        }
        return response()->json(['error'=>'Unable to logout user',
        'code'=>401,],401);
    }
}
