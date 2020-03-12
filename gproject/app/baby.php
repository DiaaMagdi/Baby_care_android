<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use app\User;

class baby extends Model
{
    protected $table ="babies";
    protected $fillable = [
        'name',
        'gender',
        'birthdate',
        'weight',
        'length',
        'user_id'
    ];
    public function user(){
        //return $this->belongsTo(User::class);
        return $this->belongsTo('app\User','user_id');
    }
}
